# Simple Crud Application with Spring Boot and Kafka

Se opta por tomar una arquitectura de microservicios con Java y SpringBoot basandose en el problema planteado para el ejercicio de prueba.
El proyecto está en paquetes de la siguiente forma:

- En el paquete .../commons tenemos las diferentes utilidades comunes para usar en el proyecto y las constantes para evitar tener texto quemado y reusado en el código.
- En el paquete .../config tenemos los bean de configuración para Kafka, Swagger y Gson.
- En el paquete .../controllers tenemos el controlador rest api con las api's para consultar todas las reservaciones, obtener por id, crear y cancelar reservación.
- En el paquete .../models/dao tenemos la interfaz IReservationDao, la cual extiende de CrudRepository para manejar la persistencia de datos mediante JPA.
- En el paquete .../models/entity tenemos la entidad Reservation bastada en la tabla creada en base de datos y el modelo ResponseMessage que se utiliza para algunas respuestas de las api rest.
- En el paquete .../models/service tenemos el service de Reservation que se encarga de el llamado a los dao, al igual que los component Consumer (con el listener) y el Producer para la conexión con el message broker de Kafka.
- Adicionalmente contamos con el folder src/test/java donde tenemos el paquete de pruebas para el CommonsUtil y el ReservationService.

## Rest Api's
Al desplegar el proyecto, podrás acceder a la ui de swagger para ver mas información acerca de las api que contiene el proyecto.
![restapi](https://user-images.githubusercontent.com/42575272/139563193-c129f0b4-4165-4e5c-9645-9fbeaa233bf1.PNG)
> Puedes acceder desde esta ruta al desplegarlo: http://localhost:8080/leantech/swagger-ui/index.html

O consumir el api 'http://localhost:8080/leantech/v2/api-docs' desde postman o un navegador para ver las diferentes api rest en formato JSON.
Este modelo le será de mucha ayuda para comprender con que entidad funciona el proyecto:
![model](https://user-images.githubusercontent.com/42575272/139563579-7d81acab-dbac-43bf-9ea5-6a94b6f0b78d.PNG)

Adicionalmente, esta es la collection usada durante las pruebas mediante postman: 'https://www.getpostman.com/collections/9e707f383189f63f02e8'

## Instalación
Antes de empezar son necesarios los siguientes prerequisitos:
- Tener instalado Eclipse o IntelliJ con el plugin de SpringBoot y de Lombok para evitar cualquier error.
- Tener instalado Docker.
- (Opcional) Tener instalado PGAdmin para hacerle seguimiento a la bd del proyecto.

Para instalar el ambiente hay que seguir los siguientes pasos:
- Clonar el repositorio para tenerlo en tu maquina.
- Importar el proyecto 'pruebaleantech' en Eclipse o IntelliJ (Según preferencia).
- Acceder a la carpeta 'postgresql' mediante linea de comando (PowerShell en windows) y ejecutar el siguiente comando:
```bash
docker-compose up -d
```
Esto levantara una imagen de Postgres en docker con la configuración necesaria para correr el proyecto sin problema.
- Hacer lo mismo en la carpeta 'kafka' para levantar un una imagen en docker con un message broker de Kafka con las configuraciones necesarias para el proyecto.
- Realizar un 'maven clean' y un 'maven install' en el proyecto para instalar las dependencias necesarias y compilarlo.
- Levantar el proyecto desde el IDE elegido mediante el servidor de Spring Boot, esto automaticamente creata  una tabla llamada 'reservation' en la base de datos 'leantechdb' que se crea automaticamente al levantar la imagen de Postgres con Docker (en la carpeta 'db' dentro de la carpeta 'postgresql' encontrara tambien un script para crear la tabla en caso de que haya algún problema).

Despues de esto ya podrá consumir las diferentes apis del proyecto. Tener en cuenta para bajar las imagenes de docker usar el siguoente comando en ambas carpetas:
```bash
docker-compose down
```

## Registro de reservas
El flujo mas complejo de la aplicación es el registro de reservas, el siguiente diagrama de secuencia puede ser de mucha ayuda: 

![secuencia](https://user-images.githubusercontent.com/42575272/139563765-ea345d2e-870d-4e0a-bc8e-1e8f7c8d76b2.jpeg)

En el podemos observar que el usuario al hacer una petición post para registrar una reserva, invoca el api rest del microservicio pruebaleantech. Este se encarga de en primer instancia validar  los datos enviados que vengan en los formatos correctos de acuerdo a los parametros especificados para la entidad. Si todo está en orden, el servicio procedera a encolar esa reserva mediante un message broker en kafka y respondera que 'la solicitud de reserva se hizo correctamente'. El listener de kafka se encargara de revisar esta cola de reservas una por una y persistirlas en base de datos. En caso tal de se haya podido guardar la información correctamente, el sistema enviara un correo electronico confirmando que la reserva se realizo exitsamente. De igual forma si ocurre un problema al persistir la información, se enviara un correo notificando que hubo un problema al registrar la reserva y se encolara al 'Dead letter queue'.


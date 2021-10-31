package com.leantech.pruebaleantech.commons;

public final class LeanTechConstants {
	
	// ENTITY ATTRIBUTES CONSTANTS
	
	public static final String COMMON_STRING_RESERVATION = "reservation";
	public static final String COMMON_STRING_ARRIVAL_DATE = "arrival_date";
	public static final String COMMON_STRING_DEPARTURE_DATE = "departure_date";
	public static final String COMMON_STRING_TOTAL_DAYS = "total_days";
	public static final String COMMON_STRING_NUMBER_OF_PEOPLE = "number_of_people";
	public static final String COMMON_STRING_RESERVATION_HOLDER = "reservation_holder";
	public static final String COMMON_STRING_NUMBER_OF_ROOMS = "number_of_rooms";
	public static final String COMMON_STRING_NUMBER_OF_MINORS = "number_of_minors";
	public static final String COMMON_STRING_RESERVATION_HOLDER_EMAIL = "reservation_holder_email";
	public static final String COMMON_STRING_LICENSE = "License";
	public static final String COMMON_STRING_MESSAGE = "mensaje";
	public static final String COMMON_STRING_ERROR = "error";
		
	// API REST CONSTANTS

	public static final String COMMON_STRING_REQUEST_MAPPING = "/api";
	public static final String COMMON_STRING_GET_ALL_RESERVATIONS_ENDPOINT = "/obtener-reservas";
	public static final String COMMON_STRING_GET_RESERVATION_BY_ID_ENDPOINT = "/consultar-reserva/{id}";
	public static final String COMMON_STRING_CREATE_RESERVATION_ENDPOINT = "/registrar-reserva";
	public static final String COMMON_STRING_CANCEL_RESERVATION_ENDPOINT = "/reservations/cancelar-reserva/{id}";
	
	// OTHER CONSTANTS
	
	public static final String COMMON_STRING_MY_NAME = "Daniel David Ruiz Escobar";
	public static final String COMMON_STRING_MY_EMAIL = "dadarues29@gmail.com";
	public static final String COMMON_STRING_MY_LINKEDIN = "https://www.linkedin.com/in/ddruiz29/";
	public static final String COMMON_STRING_LEAN_TECH_WEBSITE = "https://www.leangroup.com/solutions/leantech";
	public static final String COMMON_STRING_EMPTY = "";
	public static final String COMMON_STRING_CONTROLLER_PACKAGE_BASE = "com.leantech.pruebaleantech.controllers";
	public static final String COMMON_STRING_VERSION = "1.0.0";
	public static final String COMMON_STRING_SWAGGER_TITLE = "Reservations Rest Api";
	public static final String COMMON_STRING_SWAGGER_DESCRIPTION = "This is a reservation's microservice for the Learn Tech Test to the Java Developer Position!!";
	public static final String COMMON_STRING_DATE_FORMAT = "yyyy-MM-dd";
	public static final String COMMON_STRING_COLON = ":";
	public static final String COMMON_STRING_SPACE = " ";
	public static final String COMMON_STRING_DEAD_LETTER_QUEUE = "DEAD-LETTER-QUEUE";
	public static final String COMMON_STRING_LEAN_TOPIC = "TOPIC-RESERVATION";
	public static final String COMMON_STRING_LINE = "\n";
	
	// ERROR CONSTANTS
	
	public static final String COMMON_STRING_ERRORS= "errores";
	public static final String COMMON_STRING_SUCCESS_CODE= "0";
	public static final String COMMON_STRING_SUCCESS_MESSAGE = "SUCCESS";
	public static final String COMMON_STRING_ERROR_CODE = "500";
	public static final String COMMON_STRING_ERROR_BAD_REQUEST_CODE = "400";
	public static final String COMMON_STRING_ERROR_CANCEL_RESERVATION_MESSAGE = "Ha ocurrido un error al tratar de cancelar la reservación";
	public static final String COMMON_STRING_ERROR_DONT_CANCEL_RESERVATION_MESSAGE = "No se puede eliminar la reserva con el id recibido!";
	public static final String COMMON_STRING_ERROR_CREATE_RESERVATION_MESSAGE = "Ha ocurrido un error al tratar de solicitar una reservación.";
	public static final String COMMON_STRING_ERROR_DONT_CREATE_RESERVATION_MESSAGE = "No se pudo encolar la solicitud para la reservación. Revise la conexión al broker message.";
	public static final String COMMON_STRING_ERROR_DB_CONECTION = "Error al consultar en la base de datos";
	public static final String COMMON_STRING_ERROR_NOT_FOUND_BY_ID = "No contamos con una reserva en nuestra base de datos para el id: ";
	public static final String COMMON_STRING_SUCCESS_CANCEL_RESERVATION = "Se cancelo la reservación exitosamente.";
	public static final String COMMON_STRING_SUCCESS_CREATE_RESERVATION = "Su solicitud de reserva se realizo exitosamente. Recibira un correo en los proximos minutos confirmando la misma.";
	
	public static final String COMMON_STRING_SUCCESS_CREATE_RESERVATION_EMAIL = "La reserva fue realizada con exito!\n";
	public static final String COMMON_STRING_ERROR_CREATE_RESERVATION_EMAIL = "Hubo un problema con la reserva y no se pudo hacer exitosamente!\n";
	public static final String COMMON_STRING_STATUS_RESERVATION_EMAIL = "Estado de reserva realizada";
}

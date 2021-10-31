-- Creation of reservation table
CREATE TABLE IF NOT EXISTS reservation (
  id INT NOT NULL,
  arrival_date TIMESTAMP NOT NULL,
  departure_date TIMESTAMP NOT NULL,
  total_days INT NOT NULL,
  number_of_people INT NOT NULL,
  reservation_holder VARCHAR(50) NOT NULL,
  number_of_rooms INT NOT NULL,
  number_of_minors INT NOT NULL,
  reservation_holder_email VARCHAR(50) NOT NULL
  PRIMARY KEY (id)
);
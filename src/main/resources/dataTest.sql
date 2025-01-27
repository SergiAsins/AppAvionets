/* Roles */
INSERT INTO roles (name)
SELECT 'ROLE_USER';

INSERT INTO roles (name)
SELECT 'ROLE_ADMIN';

/* Users */
INSERT INTO users (username, password) VALUES ('Mohammed', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Abdeljawel', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

/* Roles Users */
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);

/* Profiles */
INSERT INTO profiles (user_id, name, phone, email, address, picture)
VALUES (1, 'Mohammed Hassan V', '1233456789', 'mohammed@abdjaji.ma', 'Rue Zellaka, 3, 40000 Marrakech', 'Moha picture');

INSERT INTO profiles (user_id, name, phone, email, address, picture)
VALUES (2, 'Abdeljawel Hassan VI', '111222333', 'abdel@abdjaji.sq', 'Av Ausias March', 'ProfilePicture2');

/* Airports */
INSERT INTO airport (city, code_airport, country, name) VALUES ('Delhi', 'DEL', 'India', 'Indira Gandhi International Airport');
INSERT INTO airport (city, code_airport, country, name) VALUES ('Paris', 'CDG', 'France', 'Charles de Gaulle Airport');
INSERT INTO airport (city, code_airport, country, name) VALUES ('New York', 'JFK', 'United States', 'John F. Kennedy International Airport');

/* Flights */
INSERT INTO flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats)
VALUES ('AA012', true, 1, 2, '2026-01-25T16:30:00', '2026-01-26T14:00:00', 30);

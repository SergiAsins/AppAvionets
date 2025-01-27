/* Roles */
INSERT INTO roles (name)
SELECT 'ROLE_USER';

INSERT INTO roles (name)
SELECT 'ROLE_ADMIN';

/* Users */
INSERT INTO users (username, password) VALUES ('Mohammed', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Abdeljawel', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Gandalf', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

/* Roles Users */
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);
INSERT INTO roles_users (role_id, user_id) VALUES (1, 3);


/* Profiles */
INSERT INTO profiles (user_id, name, phone, email, address, picture)
VALUES (1, 'Mohammed Hassan V', '1233456789', 'mohammed@abdjaji.ma', 'Rue Zellaka, 3, 40000 Marrakech', 'Moha picture');

INSERT INTO profiles (user_id, name, phone, email, address, picture)
VALUES (2, 'Abdeljawel Hassan VI', '111222333', 'abdel@abdjaji.sq', 'Av Ausias March', 'ProfilePicture2');

INSERT INTO profiles (user_id, name, phone, email, address, picture)
VALUES (3, 'Gandalf The White', '680222339', 'gandalf@sarumans.tlr', 'Moria Second Floor on the Left', 'ProfilePicture3');

/* Airports */
INSERT INTO airport (city, code_airport, country, name) VALUES ('Delhi', 'DEL', 'India', 'Indira Gandhi International Airport');
INSERT INTO airport (city, code_airport, country, name) VALUES ('Paris', 'CDG', 'France', 'Charles de Gaulle Airport');
INSERT INTO airport (city, code_airport, country, name) VALUES ('New York', 'JFK', 'United States', 'John F. Kennedy International Airport');

/* Flights */
INSERT INTO flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats)
VALUES ('AA012', true, 1, 2, '2026-01-25T10:00:00', '2026-01-26T06:00:00', 30);
INSERT INTO flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats)
VALUES ('AA112', true, 1, 2, '2026-01-25T16:00:00', '2026-01-26T10:00:00', 30);
INSERT INTO flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats)
VALUES ('AA013', true, 1, 3, '2026-01-25T16:30:00', '2026-01-26T14:00:00', 30);
INSERT INTO flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats)
VALUES ('AA031', true, 3, 1, '2026-01-25T16:30:00', '2026-01-27T14:00:00', 30);

/* Reservations */
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES(1, 1, 1, '2020-01-27T23:00');
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES(2, 2, 2, '2020-01-27T23:00');
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES(3, 3, 3, '2020-01-27T23:00');

UPDATE flight f
SET available_seats = available_seats - (
    SELECT COALESCE(SUM(r.seats), 0)
    FROM reservation r
    WHERE r.id_flight = f.id_flight
);
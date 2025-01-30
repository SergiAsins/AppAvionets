-- Erased data from tables and restart id identities:
TRUNCATE TABLE roles_users RESTART IDENTITY CASCADE;
TRUNCATE TABLE roles RESTART IDENTITY CASCADE;
TRUNCATE TABLE users RESTART IDENTITY CASCADE;
TRUNCATE TABLE airport RESTART IDENTITY CASCADE;
TRUNCATE TABLE flight RESTART IDENTITY CASCADE;
TRUNCATE TABLE reservation RESTART IDENTITY CASCADE;


-- Restarts sequences
ALTER SEQUENCE roles_id_role_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;


/* Roles */
INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

/* Users */
INSERT INTO users (username, password) VALUES ('Mohammed', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Abdeljawel', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Gandalf', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Frodo', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Legolas', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

/* Roles Users */
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2), (1, 1), (1, 3), (1, 4), (1, 5);

/* Profiles */
INSERT INTO profiles (user_id, name, phone, email, address, picture) VALUES (1, 'Mohammed Hassan V', '1233456789', 'mohammed@abdjaji.ma', 'Rue Zellaka, 3, 40000 Marrakech', 'Moha picture');
INSERT INTO profiles (user_id, name, phone, email, address, picture) VALUES (2, 'Abdeljawel Hassan VI', '111222333', 'abdel@abdjaji.sq', 'Av Ausias March', 'ProfilePicture2');
INSERT INTO profiles (user_id, name, phone, email, address, picture) VALUES (3, 'Gandalf The White', '680222339', 'gandalf@sarumans.tlr', 'Moria Second Floor on the Left', 'ProfilePicture3');
INSERT INTO profiles (user_id, name, phone, email, address, picture) VALUES (4, 'Frodo Baggins', '650999888', 'frodo@shire.me', 'Bag End, Hobbiton', 'ProfilePicture4');
INSERT INTO profiles (user_id, name, phone, email, address, picture) VALUES (5, 'Legolas Greenleaf', '678777555', 'legolas@rivendell.elf', 'Thranduil’s Hall, Mirkwood', 'ProfilePicture5');

/* Airports */
INSERT INTO airport (city, code_airport, country, name) VALUES ('Delhi', 'DEL', 'India', 'Indira Gandhi International Airport');
INSERT INTO airport (city, code_airport, country, name) VALUES ('Paris', 'CDG', 'France', 'Charles de Gaulle Airport');
INSERT INTO airport (city, code_airport, country, name) VALUES ('New York', 'JFK', 'United States', 'John F. Kennedy International Airport');
INSERT INTO airport (city, code_airport, country, name) VALUES ('Tokyo', 'HND', 'Japan', 'Tokyo Haneda Airport');
INSERT INTO airport (city, code_airport, country, name) VALUES ('London', 'LHR', 'United Kingdom', 'Heathrow Airport');
INSERT INTO airport (city, code_airport, country, name) VALUES ('Dubai', 'DXB', 'United Arab Emirates', 'Dubai International Airport');


/* Flights */
INSERT INTO flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats) VALUES ('AA012', true, 1, 2, '2026-01-25T10:00:00', '2026-01-26T06:00:00', 30);
INSERT INTO  flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats) VALUES ('AA112', true, 1, 2, '2026-01-25T16:00:00', '2026-01-26T10:00:00', 30);
INSERT INTO  flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats) VALUES ('AA013', true, 1, 3, '2026-01-25T16:30:00', '2026-01-26T14:00:00', 30);
INSERT INTO  flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats) VALUES ('AA031', true, 3, 1, '2026-01-25T16:30:00', '2026-01-27T14:00:00', 30);
INSERT INTO  flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats) VALUES ('BB210', true, 2, 4, '2026-01-26T12:00:00', '2026-01-27T08:00:00', 30);
INSERT INTO  flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats) VALUES ('CC505', true, 4, 5, '2026-01-27T14:00:00', '2026-01-28T10:30:00', 30);
INSERT INTO  flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats) VALUES ('DD707', true, 5, 6, '2026-01-28T19:45:00', '2026-01-29T14:00:00', 30);
INSERT INTO  flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats) VALUES ('EE808', true, 6, 1, '2026-01-29T22:00:00', '2026-01-30T16:00:00', 30);

/* Reservations */
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES (1, 1, 1, '2026-01-24T23:00');
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES (2, 2, 2, '2026-01-24T23:00');
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES (3, 3, 3, '2026-01-24T23:00');
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES (4, 4, 1, '2026-01-24T23:00');
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES (5, 5, 2, '2026-01-24T23:00');
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES (1, 6, 1, '2026-01-24T23:00');
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES (3, 7, 2, '2026-01-24T23:00');
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES (4, 8, 3, '2026-01-24T23:00');

UPDATE flight f
SET available_seats = available_seats - (
    SELECT COALESCE(SUM(r.seats), 0)
    FROM reservation r
    WHERE r.id_flight = f.id_flight
);



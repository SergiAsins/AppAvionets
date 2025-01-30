/* Roles */
INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

/* Users */
INSERT INTO users (username, password) VALUES
('Mohammed', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO'),
('Abdeljawel', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO'),
('Gandalf', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO'),
('Frodo', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO'),
('Legolas', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

/* Roles Users */
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2), (1, 1), (1, 3), (1, 4), (1, 5);

/* Profiles */
INSERT INTO profiles (user_id, name, phone, email, address, picture) VALUES
(1, 'Mohammed Hassan V', '1233456789', 'mohammed@abdjaji.ma', 'Rue Zellaka, 3, 40000 Marrakech', 'Moha picture'),
(2, 'Abdeljawel Hassan VI', '111222333', 'abdel@abdjaji.sq', 'Av Ausias March', 'ProfilePicture2'),
(3, 'Gandalf The White', '680222339', 'gandalf@sarumans.tlr', 'Moria Second Floor on the Left', 'ProfilePicture3'),
(4, 'Frodo Baggins', '650999888', 'frodo@shire.me', 'Bag End, Hobbiton', 'ProfilePicture4'),
(5, 'Legolas Greenleaf', '678777555', 'legolas@rivendell.elf', 'Thranduil’s Hall, Mirkwood', 'ProfilePicture5');

/* Airports */
INSERT INTO airport (city, code_airport, country, name) VALUES
('Delhi', 'DEL', 'India', 'Indira Gandhi International Airport'),
('Paris', 'CDG', 'France', 'Charles de Gaulle Airport'),
('New York', 'JFK', 'United States', 'John F. Kennedy International Airport'),
('Tokyo', 'HND', 'Japan', 'Tokyo Haneda Airport'),
('London', 'LHR', 'United Kingdom', 'Heathrow Airport'),
('Dubai', 'DXB', 'United Arab Emirates', 'Dubai International Airport');

/* Flights */
INSERT INTO flight (flight_number, status, origin_id, destination_id, departure_time, arrival_time, available_seats) VALUES
('AA012', true, 1, 2, '2026-01-25T10:00:00', '2026-01-26T06:00:00', 30),
('AA112', true, 1, 2, '2026-01-25T16:00:00', '2026-01-26T10:00:00', 30),
('AA013', true, 1, 3, '2026-01-25T16:30:00', '2026-01-26T14:00:00', 30),
('AA031', true, 3, 1, '2026-01-25T16:30:00', '2026-01-27T14:00:00', 30),
('BB210', true, 2, 4, '2026-01-26T12:00:00', '2026-01-27T08:00:00', 30),
('CC505', true, 4, 5, '2026-01-27T14:00:00', '2026-01-28T10:30:00', 30),
('DD707', true, 5, 6, '2026-01-28T19:45:00', '2026-01-29T14:00:00', 30),
('EE808', true, 6, 1, '2026-01-29T22:00:00', '2026-01-30T16:00:00', 30);

/* Reservations */
INSERT INTO reservation (id_user, id_flight, seats, ticket_time) VALUES
(1, 1, 1, '2026-01-24T23:00'),
(2, 2, 2, '2026-01-24T23:00'),
(3, 3, 3, '2026-01-24T23:00'),
(4, 4, 1, '2026-01-24T23:00'),
(5, 5, 2, '2026-01-24T23:00'),
(1, 6, 1, '2026-01-24T23:00'),
(3, 7, 2, '2026-01-24T23:00'),
(4, 8, 3, '2026-01-24T23:00');

UPDATE flight f
SET available_seats = available_seats - (
    SELECT COALESCE(SUM(r.seats), 0)
    FROM reservation r
    WHERE r.id_flight = f.id_flight
);

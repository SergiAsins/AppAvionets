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



DROP DATABASE IF EXISTS ensf480_g12;
CREATE DATABASE ensf480_g12;
USE ensf480_g12;

CREATE TABLE users (
	id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(128) NOT NULL,
    registered BOOLEAN DEFAULT FALSE,
    `password` VARCHAR(16) DEFAULT NULL,
    `name` VARCHAR(32) DEFAULT NULL,
    credits DOUBLE DEFAULT 0,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE cards (
	id INT NOT NULL AUTO_INCREMENT,
    name_on_card VARCHAR(32) NOT NULL,
    card_number VARCHAR(16) NOT NULL,
    expiry_date DATE NOT NULL,
    cvv CHAR(3),
    user_id INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE addresses (
	id INT NOT NULL AUTO_INCREMENT,
    street_addr VARCHAR(512) NOT NULL,
    city VARCHAR(64) NOT NULL,
    state VARCHAR(64) NOT NULL,
    country VARCHAR(64) NOT NULL,
    postal_code VARCHAR(6) NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE movie_catalogue (
	id INT NOT NULL AUTO_INCREMENT,
    `name` CHAR(255) NOT NULL,
    `description` VARCHAR(512) NOT NULL,
    length INT NOT NULL,
    available_date DATE,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE movie_offerings (
	id INT NOT NULL AUTO_INCREMENT,
    movie_id INT NOT NULL,
    showtime DATETIME NOT NULL,
    seat_price DOUBLE NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE seats (
	id INT NOT NULL AUTO_INCREMENT,
    `row` INT NOT NULL,
    col INT NOT NULL,
    `number` INT NOT NULL,
    theatre_id INT NOT NULL,
    reserved BOOLEAN DEFAULT TRUE,
    price DOUBLE NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE theatres (
	id INT NOT NULL AUTO_INCREMENT,
    `number` INT NOT NULL,
    capacity INT NOT NULL DEFAULT 150,
    seats_filled INT DEFAULT 0,
    movie_offering_id INT,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE tickets (
	id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    seat_id INT NOT NULL,
    purchase_date DATETIME NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

# create first user
INSERT INTO users (email, registered, `password`, `name`) VALUES ('user1@example.com', TRUE, 'user1', 'User One');
# create card for first user
INSERT INTO cards (name_on_card, card_number, expiry_date, cvv, user_id) VALUES ('User One', '0123456789012345', '2024-02-14', '001', 1);
# create address for first user
INSERT INTO addresses (street_addr, city, state, country, postal_code, user_id) VALUES ('100 userone St. SW', 'City', 'State', 'Country', 'A0B1C2', 1);

# Create a catalogue of movies
INSERT INTO movie_catalogue (`name`, `description`, length, available_date) VALUES ('Indiana Jones and the Raiders of the Lost Ark', 'cool whip bro', 115, NULL),
																				   ('Avengers: Endgame', 'cool gauntlet man', 182, '2019-05-06'),
                                                                                   ('The Lion King', 'cool mane dude', 90, NULL),
                                                                                   ("Fate/Stay night: Heaven's Feel - III. Spring Song", "Heaven's Feel third movie", 123, '2020-08-25'),
                                                                                   ('Kimetsu no Yaiba the Movie: Mugen Train', 'Good movie please make season 2', 120, '2020-12-24');

# Create two movie offerings per item in movie catalogue
INSERT INTO movie_offerings (movie_id, showtime, seat_price) VALUES (1, '2020-12-01 08:00:00', 14.99),
															   (1, '2020-12-02 09:00:00', 14.99),
															   (2, '2020-12-02 17:00:00', 24.99),
                                                               (2, '2020-12-03 18:00:00', 24.99),
															   (3, '2020-12-09 21:30:00', 19.99),
                                                               (3, '2020-12-10 22:30:00', 19.99),
															   (4, '2020-12-12 22:00:00', 25.99),
                                                               (4, '2020-12-13 21:00:00', 25.99),
                                                               (5, '2020-12-19 17:00:00', 29.99),
                                                               (5, '2020-12-20 19:00:00', 29.99);

# Create theatres for each movie offering
INSERT INTO theatres (movie_offering_id, `number`, capacity, seats_filled) VALUES (1, 1, DEFAULT, 1),
																				  (2, 2, DEFAULT, 1),
                                                                                  (3, 1, DEFAULT, 1),
                                                                                  (4, 2, DEFAULT, 1),
                                                                                  (5, 1, DEFAULT, 1),
                                                                                  (6, 2, DEFAULT, 1),
                                                                                  (7, 1, DEFAULT, 1),
                                                                                  (8, 2, DEFAULT, 1),
                                                                                  (9, 1, DEFAULT, 1),
                                                                                  (10, 2, DEFAULT, 1);
                                                                                  
# Create reserved seats for each movie offering's theatre
INSERT INTO seats (`row`, col, `number`, theatre_id, reserved, price) VALUES (0, 0, 1, 1, DEFAULT, 14.99),
																   (0, 1, 2, 2, DEFAULT, 14.99),
                                                                   (1, 0, 15, 3, DEFAULT, 24.99),
                                                                   (1, 1, 16, 4, DEFAULT, 24.99),
                                                                   (1, 2, 17, 5, DEFAULT, 19.99),
                                                                   (1, 2, 17, 6, DEFAULT, 19.99),
                                                                   (1, 2, 17, 7, DEFAULT, 25.99),
                                                                   (1, 2, 17, 8, DEFAULT, 25.99),
                                                                   (1, 2, 17, 9, DEFAULT, 29.99),
                                                                   (1, 2, 17, 10, DEFAULT, 29.99);
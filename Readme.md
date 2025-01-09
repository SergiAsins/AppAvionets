# Avionets Project Documentation

## Structure:
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── avionets/
│   │           ├── configuration/
│   │           ├── controller/
│   │           ├── integration/
│   │           ├── dto/
│   │           ├── mapper/
│   │           ├── model/
│   │           ├── runner/
│   │           ├── util/
│   │           └── AvionetsApiApplication.java
│   └── resources/
│       ├── application.properties
│       └── META-INF/
│           └── resources/
│               └── (Swagger UI resources)
└── test/
    └── java/
        └── com/
            └── avionets/
                └── (Test classes)

```
## Technologies:

- Java 21
- Spring Boot
- Maven
- MySQL or PostgreSQL

## Project Objectives:

1. Reinforce concepts of API creation.
2. Apply database relationships.
3. Establish knowledge of login with Spring Security using Basic Auth or JWT.

## Functional Requirements:

### Client Management:

- User registration, authentication, and role management (ROLE_ADMIN and ROLE_USER).
- Token generation and validation using JWT or session cookies for secure sessions.

### Flight Management:

- Flights are automatically populated into the database at compilation (via `.sql` file).
- Change flight status to "false" when no seats are available or after the flight date.

### Search:

- Search flights by departure airport, destination airport, date, and number of seats.

### Reservation Management:

- Allow flight reservations only if the selected route exists and seats are available.
- Verify availability before confirming a reservation.
- Lock seats for 15 minutes during the reservation process to ensure availability.

### Admin Operations (ROLE_ADMIN):

- CRUD operations for airports.
- CRUD operations for flight routes.
- Summary listing of reservations made by clients.

### Client Operations (ROLE_USER):

- Registration with profile image upload (default image if none is provided).
- Login functionality.
- View personal reservation history with flight details.
- Reservation requires prior login.

### Exception Handling:

- Customized exception handling for better error management.

## Non-Functional Requirements:

- **Security:** Protect API with Spring Security (Basic Auth or JWT).
- **Performance:** Automate flight status changes and reservation validations.
- **Availability:** Write tests to ensure system stability in production.

## Extras:

1. Dockerize the application and upload the image to Docker Hub.
2. Use GitHub Actions for CI (build and test).
3. Automate tests with Postman.

## Technical Requirements:

- Knowledge of Java programming and OOP principles.
- Familiarity with Spring, Spring Boot, and Spring Security.
- Experience with Basic Auth & JWT.
- Testing, Docker, and database management.

## Core Entities:

### User

- **idUser:** Unique identifier.
- **name:** Full name.
- **password:** Encrypted password.
- **role:** Enum (ADMIN, CLIENT).
- **age:** Integer.
- **address:** String.
- **email:** String.
- **profilePicture:** URL or file path (default if not provided).

### Flight

- **idFlight:** Unique identifier.
- **FlightNumber:** Unique flight number.
- **List:** List of passengers.
- **Status:** Boolean (true for available, false for unavailable).
- **Origin:** Foreign key to airport.
- **Destination:** Foreign key to airport.

### Reservation

- **idReservation:** Unique identifier.
- **Date:** Reservation date.
- **idUser:** Foreign key to user.
- **idFlight:** Foreign key to flight.
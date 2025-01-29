# ✈️ Avionets Project Documentation

👮‍♂️
**Avionets** is a **Java 21** flight reservation management system built with **Spring Boot**.
It provides user, flight, airport, authentication, security, and reservation management using a RESTful API built with Spring Boot's JPA repositories.
All the generated data within the system is stored and updated in a server PostgreSQL DB.

🔐
**Security** plays a Key role within the app AvioNets structure. In this project, Spring Boot Basic Auth has been implemented correctly and fully functional. There are user admin and clients that have different acces to the app features.
Public login is also possible for future Clients. Once inside the system, the user's authorization will be used as an ID in every transaction.


## 🔧 Technologies Used
- ☕ **Java 21**
- 🏗️ **Spring Boot**
- 🛢️ **MySQL / PostgreSQL**
- 🔐 **Spring Security (JWT & Basic Auth)**
- 🏗️ **Maven**
- 🐳 **Docker**


## 🎯 Project Objectives
✔️ Strengthen API development concepts.  
✔️ Apply database relationships.  
✔️ Implement authentication and security using **Spring Security** (Basic Auth).  
✔️ Apply role-based security (**ROLE_ADMIN** and **ROLE_USER**).

## ⚙️ Functional features

### 🛂 User Management
- ✅ Each user's password is securely stored.
- ✅ Users' requests directly lead them to their own bookings, and only they have access to their personal information.
- ✅ User registration with profile image upload (default image if not provided).
- ✅ Authentication with Basic Auth.
- ✅ Role management: **ROLE_ADMIN** and **ROLE_USER**.
- ✅ Users can only modify their own profile.

### 🛫 Flight Management
- 🔍 Search flights by departure airport, destination airport, date, and number of seats. This allows clients to check flight availability and list flights on a selected date before booking.
- ✈️ Flights are preloaded into the database at build time (via `.sql`).
- 🟢 Flight status automatically changes to `false` when no seats are available.
- 🛑 Flight status automatically changes to false after 30 minutes when a reservation is made by a client.
- 📢 The IATA (International Air Transport Association) code is used to search flight origins and destinations.

### 🎟️ Reservation Management
- ✅ Flight reservations are only possible if seats are available.
- ✅ Seats are locked for 30 minutes to **prevent overbooking**.
- ✅ Users can only manage their own reservations.

### 🔒 Security
- 🔑 **Spring Security** encryption of passwords.
- 👮‍♂️ **Admins** can manage flights and airports.
- 🔐 **Users** can manage their profile and reservations.

---

## 📡 **API Endpoints**

### **Users**
| Method | Endpoint                   | Description                    |
|--------|----------------------------|--------------------------------|
| PUT    | `/api/v1/users/my-user/`   | Modify the authenticated user. |
| POST   | `/api/v1/users`            | Register a new user            |
| GET    | `/api/v1/users/{id}`       | Get user by ID                 |
| GET    | `/api/v1/users/{username}` | Search users by name           |
| PUT    | `/api/v1/users/{id}`       | Update user by ID              |
| DELETE | `/api/v1/users/{id}`       | Delete user                    |

### **Authentication**
| Method | Endpoint                     | Description                            |
|--------|------------------------------|----------------------------------------|
| POST   | `/api/v1/register`           | User registration                      |
| POST   | `/api/v1/login`              | Public login and stores key encryption |

### **Profiles**
| Method | Endpoint                          | Description |
|--------|-----------------------------------|-------------|
| POST   | `/api/v1/profiles`                | Create profile (only for authenticated users) |
| PUT    | `/api/v1/profiles/modify`         | Modify profile (only for authenticated users) |
| GET    | `/api/v1/profiles/{email}`        | Get profile by email  |
| GET    | `/api/v1/profiles/users/{userId}` | Get profile by userId  |     |                                   |             |

###  **Flights**
| Method | Endpoint                     | Description                                                                                                                           |
|--------|------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| POST   | `/api/v1/flights`            | Create a flight (ADMIN)                                                                                                               |
| GET    | `/api/v1/flights`            | Get all flights                                                                                                                       |
| GET    | `/api/v1/flights/by-id/{id}` | Get flight by ID                                                                                                                      |
| GET    | `/api/v1/flights/search`     | Search flights by specified criteria: airport origin,  airport destination and the number of availability seats for a specified date. |
| PUT    | `/api/v1/flights/{id}`       | Modify flight (ADMIN)                                                                                                                 |
| DELETE | `/api/v1/flights/{id}`       | Delete flight (ADMIN)                                                                                                                 |

###  **Reservations**
| Method | Endpoint                         | Description |
|--------|----------------------------------|-------------|
| POST   | `/api/v1/reservations`          | Create reservation (only authenticated users) |
| GET    | `/api/v1/reservations`          | Get all reservations (ADMIN) |
| GET    | `/api/v1/reservations/my-reservations` | Get reservations of authenticated user |
| GET    | `/api/v1/reservations/users/{userId}` | Get reservations of a specific user (ADMIN) |
| PUT    | `/api/v1/reservations/{id}`     | Modify an existing reservation of the authenticated user. |
| DELETE | `/api/v1/reservations/{id}`     | Delete reservation of authenticated user |

###  **Airports**
| Method | Endpoint                     | Description |
|--------|------------------------------|-------------|
| POST   | `/api/v1/airports`           | Create airport (ADMIN) |
| GET    | `/api/v1/airports`           | Get all airports |
| GET    | `/api/v1/airports/by-code/{codeAirport}` | Get airport by code |
| GET    | `/api/v1/airports/by-id/{id}` | Get airport by ID |
| PUT    | `/api/v1/airports/by-id/{id}` | Modify airport (ADMIN) |
| DELETE | `/api/v1/airports/{id}`       | Delete airport (ADMIN) |

---
## 🔐 **Configuration Profiles**
The system allows two configurations:
1. **🔬 `test`** - For testing with **H2 Database**.
2. **🌐 `postgres`** - Production setup with **PostgreSQL**.


🚀 Extras
✔️ Docker: The application is fully dockerized and ready for deployment.
✔️ GitHub Actions: CI/CD pipeline implemented.
✔️ Automated Testing: Tests with Postman and JUnit.




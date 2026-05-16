# Courier Management Spring Boot API

Spring Boot backend for authentication, JWT-based authorization, pricing, and the main courier order lifecycle.

## Tech Stack
- Java 17
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- MySQL
- JWT

## What This Project Handles
- User login
- Employee login
- JWT generation
- Order creation
- User active and completed orders
- Employee available, taken, and completed orders
- Pricing quote calculation
- Order status transitions

## Default Local Runtime
- Base URL: `http://localhost:9090`
- Database: `courier_managment`

## Important API Areas

### Authentication
- `POST /both/userlogin`
- `POST /both/employeelogin`

### User
- `POST /saveorder`
- `GET /user/getorders/{email}`
- `GET /user/getcompletedorders/{email}`
- `GET /user/getcancelledorders/{email}`
- `POST /user/pricing/quote`

### Employee
- `GET /employee/getallorders`
- `POST /employee/takenorders`
- `POST /employee/updateorder`
- `GET /employee/getpendingorders/{email}`
- `GET /employee/employeecompletedorders/{email}`
- `GET /employee/takenorderchangestatus/{tracking}/{status}/{date}`
- `GET /employee/allorderschangestatus/{tracking}/{status}/{date}`

## Setup
1. Configure MySQL and create the `courier_managment` database.
2. Update `src/main/resources/application.properties` for your local database credentials if needed.
3. Make sure the MVC application is also running if you want the full end-to-end system.

## Run
Use the Maven wrapper:

```bash
./mvnw spring-boot:run
```

Or on Windows:

```powershell
.\mvnw spring-boot:run
```

## Build
```bash
./mvnw clean package
```

## Notes
- The React app calls this service using `VITE_SPRING_API_URL=http://localhost:9090`.
- Security is role-based with user and employee routes.
- This service is the main source of truth for courier order state.

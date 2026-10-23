# AutoLoc

AutoLoc is a Spring Boot REST API for managing a vehicle rental business across agencies. The current project includes persistence models, repositories, service classes, and CRUD endpoints for the main rental operations.

## Current features

- Manage vehicles and their agency, equipment, maintenance, and reservation relationships.
- Manage agencies, employees, clients, reservations, contracts, payments, equipment, and maintenance records.
- Create, list, retrieve, update, and delete records through REST endpoints.
- Initialize three example vehicles on startup when the vehicle table is empty.
- Expose generated API documentation through Springdoc OpenAPI.

Authentication, authorization, a client-facing interface, and complete rental workflows are not present in the current codebase.

## Technology

- Java 17
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA / Hibernate
- MySQL Connector/J
- Lombok
- Springdoc OpenAPI UI
- Maven Wrapper

## Requirements

- JDK 17 or newer
- MySQL server
- Maven is optional; the included Maven Wrapper can be used instead

## Configuration

The default configuration is in `src/main/resources/application.properties` and activates the `dev` profile. By default, the application connects to:

```text
URL:      jdbc:mysql://127.0.0.1:3306/autoloc?createDatabaseIfNotExist=true
Username: root
Password: (empty)
Port:     8081
```

Update `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` for your local MySQL setup. Hibernate is configured with `spring.jpa.hibernate.ddl-auto=update`; use an appropriately managed schema configuration for deployments.

## Run the application

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8081`.

## REST endpoints

Each resource currently supports the standard CRUD operations:

| Resource | Base path |
|---|---|
| Vehicles | `/api/vehicules` |
| Agencies | `/api/agences` |
| Clients | `/api/clients` |
| Employees | `/api/employes` |
| Reservations | `/api/reservations` |
| Contracts | `/api/contrats` |
| Payments | `/api/paiements` |
| Equipment | `/api/equipements` |
| Maintenance | `/api/maintenances` |

For each base path, `GET` lists records, `GET /{id}` retrieves one record, `POST` creates a record, `PUT /{id}` updates it, and `DELETE /{id}` removes it. Request and response bodies use JSON. For example:

```text
GET    http://localhost:8081/api/vehicules
GET    http://localhost:8081/api/vehicules/1
POST   http://localhost:8081/api/vehicules
PUT    http://localhost:8081/api/vehicules/1
DELETE http://localhost:8081/api/vehicules/1
```

The controllers return `404 Not Found` when an item is requested, updated, or deleted by an unknown ID; successful deletes return `204 No Content`.

## API documentation

Springdoc OpenAPI UI is included. When the application is running, try:

```text
http://localhost:8081/swagger-ui/index.html
```

The OpenAPI document is typically available at `/v3/api-docs`.

## Project layout

```text
src/main/java/tn/esprit/autoloc/
├── config/       Startup data initialization
├── controller/   REST endpoints
├── domain/       JPA entities, enums, and service classes
├── repository/   Spring Data repositories
└── AutolocApplication.java
src/main/resources/
└── application*.properties
src/test/java/    Application context test
```

## Build

```powershell
.\mvnw.cmd package
```

The project contains a Spring Boot context-load test under `src/test/java`. Run the test suite with `./mvnw test` (or `mvnw.cmd test` on Windows); the application requires a reachable, configured MySQL database when its persistence context starts.

# BrickSet Tracker - Spring Boot API

A RESTful API built with Java and Spring Boot. This is a WIP re-implementation of the core backend logic from my original [Python, Flask application](https://github.com/RobertLippai/brickset_tracker).

The primary goal is to demonstrate my ability to rapidly learn and apply new technologies within the Java/Spring ecosystem. Currently it provides CRUD operations for storing Brick (Lego) sets.

## Tech
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 Database (In-Memory)

## Current Features
- Full CRUD operations for brick sets.
- A RESTful API architecture.

## How to Run
**Prerequisites:**
- JDK 17 or later
- Apache Maven

**Clone the repository:**
```bash
git clone https://github.com/RobertLippai/brickset-tracker-spring-api.git
cd brickset-tracker-spring-api
```

**Run the application using Maven:**
```bash
mvn spring-boot:run
```

The API will be available at http://localhost:8080.

## API Endpoints
Currently, only the Sets controller is implemented. 
The endpoints below all use the base URL: /api/sets


| Method | Endpoint | Description                              |
|--------|----------|------------------------------------------|
| GET    | /        | Retrieves a list of all Brick sets.      |
| GET    | /{id}    | Retrieves a single Brick set by its ID.  |
| POST   | /        | Creates a new Brick set.                 |
| PUT    | /{id}    | Updates an existing Brick set by its ID. |
| DELETE | /{id}    | Deletes a Brick set by its ID.           |


## Roadmap
**The following features are planned to bring it closer to the original Flask application and beyond:**
- Migrate from the in-memory database to a persistent one, like PostgreSQL.
- Implement security using Spring Security and JWT.
- Add more complex query endpoints (searching, filtering, etc.).
- Connect this API to a standalone frontend application.
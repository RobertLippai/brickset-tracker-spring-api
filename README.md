# BrickSet Tracker - Spring Boot API

A RESTful API built with Java and Spring Boot. This is a WIP re-implementation of the core backend logic from my original [Python, Flask application](https://github.com/RobertLippai/brickset_tracker).

The primary goal is to demonstrate my ability to rapidly learn and apply new technologies within the Java/Spring ecosystem.

The API is now secured using JWT-based authentication and authorization. All endpoints, except for registration and login, require a valid JWT token for access.

## Tech
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- H2 Database (In-Memory)
- JJWT (JSON Web Token Library)

## Current Features
- Full CRUD operations for brick sets and brands.
- A RESTful API architecture.

## Configuration
To run the application, you must set the following environment variables:

| Variable Name    | Description                                                                                             | Example Value                                |
| ---------------- | ------------------------------------------------------------------------------------------------------- | -------------------------------------------- |
| `JWT_SECRET_KEY` | A long, random, Base64-encoded string for signing JWTs. You can generate one [here](https://www.devglan.com/online-tools/hmac-sha256-online). | `your-super-long-and-secure-base64-secret-key` |
| `DB_PASSWORD`    | The password for the H2 in-memory database. The default username is `sa`.                                 | `password`                                   |
    
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
The API provides CRUD operations for Sets, Brands and Tags.

### Authentication Controller
Base URL: `/api/auth`
_(Public Endpoints)_

| Method | Endpoint    | Description                                     |
|--------|-------------|-------------------------------------------------|
| POST   | `/register` | Creates a new user and returns a JWT.      |
| POST   | `/login`    | Authenticates an existing user and returns a JWT. |

### User Controller
Base URL: `/api/me`
_(Requires Authentication)_

| Method | Endpoint          | Description                                                      |
|--------|-------------------|------------------------------------------------------------------|
| GET    | `/sets`           | Retrieves all the sets from the current user's inventory.        |
| POST   | `/sets`           | Adds a set to the current user's inventory.                      |
| DELETE | `/sets/{setId}`   | Removes a set from the current user's inventory.                 |


### Sets Controller
The endpoints below all use the base URL: /api/sets

| Method | Endpoint | Description                               | Security                |
|--------|----------|-------------------------------------------|-------------------------|
| GET    | /        | Retrieves a list of all Brick sets.       | Public                  |
| GET    | /{id}    | Retrieves a single Brick set by its ID.   | Public                  |
| POST   | /        | Creates a new Brick set.                  | Requires Authentication |
| PUT    | /{id}    | Updates an existing Brick set by its ID.  | Requires Authentication |
| DELETE | /{id}    | Deletes a Brick set by its ID.            | Requires Authentication |
| POST   | `/{id}/tags`      | Adds a tag to the set.           | Requires Authentication |
| DELETE | `/{id}/tags/{tagId}` | Removes a tag from the set.   | Requires Authentication |

### Brands Controller
The endpoints below all use the base URL: `/api/brands`

| Method | Endpoint | Description                               | Security                |
|--------|----------|-------------------------------------------|-------------------------|
| GET    | /        | Retrieves a list of all Brick brands.     | Public                  |
| GET    | /{id}    | Retrieves a single Brick brand by its ID. | Public                  |
| POST   | /        | Creates a new Brick brand.                | Requires Authentication |
| PUT    | /{id}    | Updates an existing Brick brand by its ID.| Requires Authentication |
| DELETE | /{id}    | Deletes a Brick brand by its ID.          | Requires Authentication |

### Tags Controller
Base URL: `/api/tags`

| Method | Endpoint | Description                              | Security                |
|--------|----------|------------------------------------------|-------------------------|
| GET    | /        | Retrieves a list of all tags.            | Public                  |
| GET    | /{id}    | Retrieves a single tag by its ID.        | Public                  |
| POST   | /        | Creates a new tag.                       | Requires Authentication |
| PUT    | /{id}    | Updates an existing tag by its ID.       | Requires Authentication |
| DELETE | /{id}    | Deletes a tag by its ID.                 | Requires Authentication |


## Roadmap
**The following features are planned to bring it closer to the original Flask application and beyond:**
- [X] **Implement security using Spring Security and JWT.**
- [ ] **Implement Role-Based Authorization**
  -   Secure `POST`, `PUT`, and `DELETE` endpoints for sets, brands, and tags so they are only accessible to users with an `EDITOR` or `ADMIN` role. 
- [ ] **Migrate to a Persistent Database**
  - Migrate from the in-memory database to a persistent one, like PostgreSQL.
- [ ] **Advanced Querying**
  - Add more complex query endpoints (searching, filtering, etc.).
-   [ ] **Build a Standalone Frontend**
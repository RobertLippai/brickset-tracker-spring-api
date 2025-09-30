# BrickSet Tracker - Spring Boot API

[![Fly Deploy](https://github.com/RobertLippai/brickset-tracker-api/actions/workflows/fly-deploy.yml/badge.svg)](https://github.com/RobertLippai/brickset-tracker-api/actions/workflows/fly-deploy.yml)


This project serves as the backend for the new **[Brickset Tracker - Vue Frontend](https://github.com/RobertLippai/brickset-tracker-frontend)**, and is a re-implementation of the logic from my original [Flask application](https://github.com/RobertLippai/brickset_tracker).

The API is secured using JWT-based authentication and authorization. All endpoints, except for registration and login, require a valid JWT token for access.

## Live API Endpoint

The API is deployed on Fly.io and is live. You can interact with the public endpoints using tools like Postman, Insomnia, or `curl`.

*   **Base URL:** `https://brickset-tracker-spring-api.fly.dev/api/sets`
*   **API Base Path:** `/api`

**Example:** To get all sets, you can make a `GET` request to `https://brickset-tracker-spring-api.fly.dev/api/sets`.

## Tech
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- H2 Database (In-Memory)
- JJWT (JSON Web Token Library)
- Containerized with Docker & deployed on Fly.io 

## Current Features
- Full CRUD operations for brick sets and brands.
- A RESTful API architecture.

## Configuration
To run the application, you must set the following environment variables:

| Variable Name    | Description                                                                                             | Example Value                                |
| ---------------- | ------------------------------------------------------------------------------------------------------- | -------------------------------------------- |
| `JWT_SECRET_KEY` | A long, random, Base64-encoded string for signing JWTs. You can generate one [here](https://www.devglan.com/online-tools/hmac-sha256-online). | `your-super-long-and-secure-base64-secret-key` |
| `DB_PASSWORD`    | The password for the H2 in-memory database. The default username is `sa`.                                 | `password`                                   |
    
## How to Run Locally
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
| POST   | /        | Creates a new Brick set.                  | Requires `ROLE_EDITOR`  |
| PUT    | /{id}    | Updates an existing Brick set by its ID.  | Requires `ROLE_EDITOR`  |
| DELETE | /{id}    | Deletes a Brick set by its ID.            | Requires `ROLE_EDITOR`  |
| POST   | `/{id}/tags`      | Adds a tag to the set.           | Requires `ROLE_EDITOR`  |
| DELETE | `/{id}/tags/{tagId}` | Removes a tag from the set.   | Requires `ROLE_EDITOR`  |

### Brands Controller
The endpoints below all use the base URL: `/api/brands`

| Method | Endpoint | Description                               | Security                |
|--------|----------|-------------------------------------------|-------------------------|
| GET    | /        | Retrieves a list of all Brick brands.     | Public                  |
| GET    | /{id}    | Retrieves a single Brick brand by its ID. | Public                  |
| POST   | /        | Creates a new Brick brand.                | Requires `ROLE_EDITOR`  |
| PUT    | /{id}    | Updates an existing Brick brand by its ID.| Requires `ROLE_EDITOR`  |
| DELETE | /{id}    | Deletes a Brick brand by its ID.          | Requires `ROLE_EDITOR`  |

### Tags Controller
Base URL: `/api/tags`

| Method | Endpoint | Description                              | Security                |
|--------|----------|------------------------------------------|-------------------------|
| GET    | /        | Retrieves a list of all tags.            | Public                  |
| GET    | /{id}    | Retrieves a single tag by its ID.        | Public                  |
| POST   | /        | Creates a new tag.                       | Requires `ROLE_EDITOR`  |
| PUT    | /{id}    | Updates an existing tag by its ID.       | Requires `ROLE_EDITOR`  |
| DELETE | /{id}    | Deletes a tag by its ID.                 | Requires `ROLE_EDITOR`  |


## Roadmap
**The following features are planned to bring it closer to the original Flask application and beyond:**
- [X] **Implement security using Spring Security and JWT.**
- [X] **Implement Role-Based Authorization**
  -   Secure `POST`, `PUT`, and `DELETE` endpoints for sets, brands, and tags so they are only accessible to users with an `EDITOR` or `ADMIN` role. 
- [X] **CI/CD Integration:** Set up GitHub Actions to automatically deploy on push to main.
- [ ] **Migrate to a Persistent Database**
  - Migrate from the in-memory database to a persistent one, like PostgreSQL.
- [ ] **Advanced Querying**
  - Add more complex query endpoints (searching, filtering, etc.).
-   [ ] **Build a Standalone Frontend**

## Disclaimer
> This project was created to showcase my work. The LEGO® sets and other brand assets (Lumibricks, Pantasy, etc.) shown are used for illustrative and demonstration purposes only. All trademarks and copyrights are the property of their respective owners. No copyright infringement is intended.

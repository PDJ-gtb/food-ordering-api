# Food Ordering API

Backend portfolio project built with **Java**, **Spring Boot**, **PostgreSQL**, and **Spring Security**.

This application simulates the backend of a food ordering platform and was developed to demonstrate practical backend skills such as:

- designing REST APIs
- building layered Spring Boot applications
- working with relational databases and JPA relationships
- validating requests and handling errors cleanly
- writing service unit tests
- implementing authentication with Spring Security
- generating and validating JWT tokens
- applying role-based authorization

---

## Why this project

The goal of this project is to showcase hands-on backend development skills through a realistic API, not just isolated coding exercises.

It reflects the kind of work involved in junior backend development roles, including:

- CRUD operations
- DTO-based architecture
- database relationships
- validation and exception handling
- testable service logic
- authentication and authorization
- secure password handling
- JWT-based request protection

---

## Tech Stack

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL**
- **Hibernate**
- **Maven**
- **JUnit 5**
- **Mockito**
- **JWT (JJWT)**

---

## Main Features

### Category Management

- Create categories
- Retrieve all categories
- Retrieve category by id
- Update categories
- Delete categories

### Dish Management

- Create dishes
- Retrieve all dishes
- Retrieve dish by id
- Update dishes
- Delete dishes
- Assign dishes to categories

### Role Management

- Create roles
- Retrieve all roles
- Retrieve role by id

### User Management

- Create users
- Retrieve all users
- Retrieve user by id
- Assign roles to users
- Store passwords securely with BCrypt
- Prevent duplicate usernames and emails

### Authentication

- Login with username and password
- Validate credentials against database users
- Generate JWT token on successful login
- Authenticate protected requests through a JWT filter
- Restrict endpoints by user role

---

## Database Design

The project includes relational modeling with JPA/Hibernate:

- **Dish → Category** (`ManyToOne`)
- **AppUser → Role** (`ManyToMany`)

This demonstrates practical knowledge of entity relationships and relational database design.

---

## API Design Highlights

This project follows common backend best practices such as:

- clear separation between entity, DTO, repository, service, controller, and security layers
- request validation with `jakarta.validation`
- centralized exception handling with `@RestControllerAdvice`
- DTO-based API responses instead of exposing entities directly
- clean service layer with single-responsibility methods
- custom business exceptions for duplicate resources and missing entities

---

## Testing

The service layer includes **unit tests** built with:

- **JUnit 5**
- **Mockito**

The tests cover:

- successful service operations
- not found scenarios
- update and delete flows
- entity relationship validation
- business logic isolation through mocking

This was implemented to demonstrate both development and software quality practices.

---

## Security

The application implements a custom Spring Security authentication flow including:

- custom `UserDetailsService`
- BCrypt password hashing
- login authentication with Spring Security
- JWT generation after successful authentication
- JWT validation for protected requests
- custom `OncePerRequestFilter` for token processing
- role-based authorization using Spring Security authorities

### Authorization Rules

| Endpoint | Access |
|----------|--------|
| `/api/v1/auth/**` | Public |
| `/api/v1/users/**` | `ADMIN` |
| `/api/v1/roles/**` | `ADMIN` |
| `/api/v1/dishes/**` | `CUSTOMER` |
| `/api/v1/categories/**` | `CUSTOMER` |

The security layer was implemented from scratch to understand the complete authentication and authorization flow instead of relying solely on default configurations.

---

## Example Endpoints

### Categories

```http
GET    /api/v1/categories
GET    /api/v1/categories/{id}
POST   /api/v1/categories
PUT    /api/v1/categories/{id}
DELETE /api/v1/categories/{id}
```

### Dishes

```http
GET    /api/v1/dishes
GET    /api/v1/dishes/{id}
POST   /api/v1/dishes
PUT    /api/v1/dishes/{id}
DELETE /api/v1/dishes/{id}
```

### Roles

```http
GET    /api/v1/roles
GET    /api/v1/roles/{id}
POST   /api/v1/roles
```

### Users

```http
GET    /api/v1/users
GET    /api/v1/users/{id}
POST   /api/v1/users
```

### Authentication

```http
POST /api/v1/auth/login
```

---

## Example Requests

### Create User

```json
{
  "username": "pedro",
  "email": "pedro@example.com",
  "password": "test123",
  "enabled": true,
  "roleIds": [1, 2]
}
```

### Login

```json
{
  "username": "pedro",
  "password": "test123"
}
```

### Create Dish

```json
{
  "name": "Cheeseburger",
  "description": "Double smash burger with cheddar",
  "price": 12.50,
  "available": true,
  "categoryId": 1
}
```

---

## What this project demonstrates

This repository showcases practical skills relevant to junior backend development roles, including:

- object-oriented programming with Java
- layered Spring Boot architecture
- RESTful API design
- PostgreSQL integration with JPA/Hibernate
- secure authentication and password management
- JWT-based stateless authentication
- role-based authorization
- request validation and centralized exception handling
- unit testing with JUnit 5 and Mockito
- clean, maintainable, and scalable backend development practices

---

## Current Status

### Implemented

- Category CRUD
- Dish CRUD
- Role creation and retrieval
- User creation and retrieval
- Duplicate username/email validation
- Entity relationships with JPA
- Validation and exception handling
- Service-layer unit testing
- Spring Security integration
- BCrypt password hashing
- JWT authentication
- JWT authorization filter
- Role-based endpoint protection

### Planned Improvements

- Swagger / OpenAPI documentation
- Docker optimization
- Cloud deployment
- Integration testing
- CI/CD pipeline

---

## Running the Project

```bash
mvn spring-boot:run
```

Environment-specific configuration is managed through `application.properties`.

An `application-example.properties` template is included to simplify project setup while keeping sensitive configuration out of version control.

---

## Author

Developed by **Francisco de Jesús Gil** as a backend portfolio project to demonstrate practical Java and Spring Boot development skills.

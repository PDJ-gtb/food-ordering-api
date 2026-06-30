# Food Ordering API

Backend portfolio project built with **Java**, **Spring Boot**, **PostgreSQL**, and **Spring Security**.

This application simulates the backend of a food ordering platform and was developed to demonstrate practical backend skills such as:

* designing REST APIs
* building layered Spring Boot applications
* working with relational databases and JPA relationships
* validating requests and handling errors cleanly
* writing service unit tests
* implementing authentication with Spring Security
* generating JWT tokens for login

---

## Why this project

The goal of this project is to showcase hands-on backend development skills through a realistic API, not just isolated coding exercises.

It reflects the kind of work involved in junior backend development roles, including:

* CRUD operations
* DTO-based architecture
* database relationships
* validation and exception handling
* testable service logic
* authentication and security fundamentals

---

## Tech Stack

* **Java**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Spring Security**
* **PostgreSQL**
* **Hibernate**
* **Maven**
* **JUnit 5**
* **Mockito**
* **JWT (JJWT)**

---

## Main Features

### Category Management

* Create categories
* Retrieve all categories
* Retrieve category by id
* Update categories
* Delete categories

### Dish Management

* Create dishes
* Retrieve all dishes
* Retrieve dish by id
* Update dishes
* Delete dishes
* Assign dishes to categories

### Role Management

* Create roles
* Retrieve all roles
* Retrieve role by id

### User Management

* Create users
* Retrieve all users
* Retrieve user by id
* Assign roles to users
* Store passwords securely with BCrypt

### Authentication

* Login with username and password
* Validate credentials against database users
* Generate JWT token on successful login

---

## Database Design

The project includes relational modeling with JPA/Hibernate:

* **Dish -> Category** (`ManyToOne`)
* **AppUser -> Role** (`ManyToMany`)

This helps demonstrate practical knowledge of entity relationships and database structure design.

---

## API Design Highlights

This project follows common backend best practices such as:

* separation between entity, DTO, repository, service, and controller layers
* request validation with `jakarta.validation`
* centralized exception handling with `@RestControllerAdvice`
* API responses based on DTOs instead of exposing entities directly
* clean service methods with focused responsibilities

---

## Testing

The service layer includes **unit tests** built with:

* **JUnit 5**
* **Mockito**

These tests cover:

* successful service operations
* not found scenarios
* update and delete flows
* related entity validation

This was added to demonstrate not only implementation skills, but also code reliability and testability.

---

## Security

The application includes the foundations of backend security:

* custom `UserDetailsService`
* BCrypt password encoding
* login with Spring Security
* JWT generation after successful authentication

This project is being developed step by step to understand the security flow in depth rather than relying only on automatic configuration.

---

## Example Endpoints

### Categories

* `GET /api/v1/categories`
* `GET /api/v1/categories/{id}`
* `POST /api/v1/categories`
* `PUT /api/v1/categories/{id}`
* `DELETE /api/v1/categories/{id}`

### Dishes

* `GET /api/v1/dishes`
* `GET /api/v1/dishes/{id}`
* `POST /api/v1/dishes`
* `PUT /api/v1/dishes/{id}`
* `DELETE /api/v1/dishes/{id}`

### Roles

* `GET /api/v1/roles`
* `GET /api/v1/roles/{id}`
* `POST /api/v1/roles`

### Users

* `GET /api/v1/users`
* `GET /api/v1/users/{id}`
* `POST /api/v1/users`

### Authentication

* `POST /api/v1/auth/login`

---

## Example Requests

### Create User

```json id="mk4y06"
{
  "username": "pedro",
  "email": "pedro@example.com",
  "password": "test123",
  "enabled": true,
  "roleIds": [1, 2]
}
```

### Login

```json id="qmyrtf"
{
  "username": "pedro",
  "password": "test123"
}
```

### Create Dish

```json id="cr1k0x"
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

This repository is intended to highlight skills relevant to junior backend developer positions:

* object-oriented programming in Java
* Spring Boot application structure
* REST API development
* PostgreSQL integration
* authentication and password security
* unit testing and debugging
* building a project progressively with clean organization

---

## Current Status

Implemented:

* category CRUD
* dish CRUD
* role creation and retrieval
* user creation and retrieval
* JPA relationships
* validation and exception handling
* service unit testing
* Spring Security integration
* BCrypt password hashing
* JWT generation on login

Planned improvements:

* JWT validation in protected requests
* role-based authorization
* Swagger / OpenAPI documentation
* Docker polish
* deployment for public testing

---

## Running the Project

```bash id="4r7mc5"
mvn spring-boot:run
```

Database configuration is set through `application.properties`.

---

## Author

Portfolio project developed by **Francisco de Jesús Gil**.

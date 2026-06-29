# Food Ordering API

Production-style REST API for a food ordering system built with **Java 21**, **Spring Boot**, **Spring Data JPA**, **Spring Validation** and **PostgreSQL**.

This project is being developed as a backend portfolio project focused on demonstrating skills that are commonly required for **Java Backend Developer** roles.

## Current features

* CRUD for **dishes**
* CRUD for **categories**
* Validation with `@Valid`
* Global exception handling with `@RestControllerAdvice`
* Relational mapping between **Dish** and **Category**
* PostgreSQL integration with Spring Data JPA

## Tech stack

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Validation
* PostgreSQL
* Maven

## Domain model

Current entities:

* **Category**

  * `id`
  * `name`
  * `description`

* **Dish**

  * `id`
  * `name`
  * `description`
  * `price`
  * `available`
  * `category`

Relationship:

* One **Category** can contain many **Dishes**
* One **Dish** belongs to one **Category**

## API endpoints

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

## Example requests

### Create category

```http
POST /api/v1/categories
Content-Type: application/json

{
  "name": "Pizzas",
  "description": "Italian pizzas and baked dishes"
}
```

### Create dish

```http
POST /api/v1/dishes
Content-Type: application/json

{
  "name": "Pizza Carbonara",
  "description": "Pizza con mozzarella, bacon y salsa carbonara",
  "price": 12.50,
  "available": true,
  "categoryId": 1
}
```

## Validation and error handling

The API includes:

* request validation with Jakarta Validation
* global exception handling
* clear JSON responses for validation errors
* `404 Not Found` handling for missing resources

Example validation error:

```json
{
  "categoryId": "must not be null"
}
```

## Project structure

```text
src/main/java/com/restaurant/order/api
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exceptions
├── repository
└── service
```

## How to run locally

### 1. Clone the repository

```bash
git clone https://github.com/PDJ-gtb/food-ordering-api.git
cd food-ordering-api
```

### 2. Configure the database

Set your PostgreSQL connection in `application.properties` or preferably through environment variables.

Example properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

Or run it directly from IntelliJ.

## Current status

This project is still under active development.

Next planned steps:

* Spring Security
* JWT authentication
* roles and authorization
* Flyway migrations
* Docker and Docker Compose
* testing with JUnit, Mockito and Testcontainers
* Swagger / OpenAPI
* CI/CD with GitHub Actions
* AWS deployment

## Goal of the project

The goal of this repository is not only to practice Spring Boot, but to build a **production-style backend project** that reflects the structure, quality and features expected from a solid **Junior Java Backend Developer**.

## Author

Backend portfolio project by **PDJ-GTB**.

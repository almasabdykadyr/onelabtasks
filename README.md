## One Lab Tasks

# Book Rental Service API

## Overview
This project provides a **Book Rental Service API** that allows users to manage books, users, and rentals. It supports operations such as adding users and books, renting books, returning them, and fetching rental data. Built using **Spring Boot**, this API offers a simple interface for library or book rental management.

## Features
- **User Management**: Add new users.
- **Book Management**: Add books with metadata (ISBN, title, description, etc.).
- **Rentals**:
    - Rent a book to a user with a 10-day due date.
    - Return a rented book.
    - Fetch all rentals, or filter by user or book.
    - Auto change rent status to overdue by given time (Spring Scheduler used).
## Technologies Used
- **Java** (Spring Boot)
- **JPA/Hibernate** (for persistence)
- **Lombok** (for reducing boilerplate code)
- **Spring Security** (for security + JWT)
- **Flyway** (for DB migrations)
- **Spring Validation** (for validation)
- **Modelmapper** (for mapping entities)
- **Kafka** (for messaging)
- **Swagger** (for interactive documentation)
- **Docker** (for convenient launch on a local server (by using docker compose))
---
## Setup Instructions

1. **Clone the repository**:
   ```bash
   https://github.com/almasabdykadyr/onelabtasks/tree/task1.git
   cd onelabtasks
   ```
2. **Run docker compose**:
    ```bash
    docker-compose up -d
    ```

2. **Build the project**:
   ```bash
   ./mvnw clean install
   ```

3. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API**:  
   The API will be available at [http://localhost:8080](http://localhost:8080).

5. **You can use Postman collection**:
       Use this [file](Book%20Rent%20Service%20Collection.postman_collection.json)

6. **Swagger API docks available at**:
       [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
---

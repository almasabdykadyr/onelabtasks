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
    - Auto change rent status to overdue by given time.
## Technologies Used
- **Java** (Spring Boot)
- **JPA/Hibernate** (for persistence)
- **Lombok** (for reducing boilerplate code)

---

## API Endpoints

### **User Management**
- **Add User**  
  **POST** `/api/rentals/users`  
  **Request Body:**
  ```json
  {
    "email": "user@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
  }
  ```

### **Book Management**
- **Add Book**  
  **POST** `/api/rentals/books`  
  **Request Body:**
  ```json
  {
    "isbn": "978-1234567890",
    "title": "Effective Java",
    "description": "A programming book on Java best practices",
    "publishedAt": "2020-01-15"
  }
  ```

### **Rentals**
- **Rent a Book**  
  **POST** `/api/rentals/rent`  
  **Request Body:**
  ```json
  {
    "userId": 1,
    "bookId": 1
  }
  ```

- **Return a Book**  
  **POST** `/api/rentals/return/{rentId}`

- **List All Rentals**  
  **GET** `/api/rentals`

- **Get Rentals by User**  
  **GET** `/api/rentals/user/{userId}`

- **Get Rentals by Book**  
  **GET** `/api/rentals/book/{bookId}`

---

## Data Models

### **User**
```java
User {
    Long id;
    String email;
    String password;
    String firstName;
    String lastName;
}
```

### **Book**
```java
Book {
    Long id;
    String isbn;
    String title;
    String description;
    LocalDate publishedAt;
    LocalDateTime createdAt;
}
```

### **Rental**
```java
Rental {
    Long id;
    User user;
    Book book;
    RentStatus status;  // RENTED, RETURNED
    LocalDate dueDate;
}
```

---

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repo/book-rental-service.git
   cd book-rental-service
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

---

## Future Improvements
- Implement **authentication and authorization** (e.g., JWT).
- Add **pagination** for listing rentals.
- Support **overdue book alerts**.
- Implement **unit and integration tests**.
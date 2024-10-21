SET SCHEMA PUBLIC;

CREATE TABLE authors
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname  VARCHAR(255) NOT NULL,
    lastname   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL
);

CREATE TABLE books
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    isbn         VARCHAR(17),
    title        VARCHAR(255),
    description  VARCHAR(500),
    author_id    BIGINT    NOT NULL,
    published_at DATE,
    created_at   TIMESTAMP NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors (id)
);

CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    firstName  VARCHAR(255) NOT NULL,
    lastName   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL
);

CREATE TABLE rentals
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id    BIGINT      NOT NULL,
    user_id    BIGINT      NOT NULL,
    status     VARCHAR(50) NOT NULL,
    due_date   DATE        NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

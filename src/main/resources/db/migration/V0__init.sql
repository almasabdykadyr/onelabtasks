CREATE TABLE authors
(
    id        BIGINT       NOT NULL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP    NOT NULL
);

CREATE TABLE books
(
    id           BIGINT    NOT NULL PRIMARY KEY,
    isbn         VARCHAR(17),
    title        VARCHAR(255),
    description  VARCHAR(500),
    author_id    BIGINT    NOT NULL,
    published_at DATE,
    created_at   TIMESTAMP NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE users
(
    id        BIGINT       NOT NULL PRIMARY KEY,
    email     VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName  VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP    NOT NULL
);

CREATE TABLE rentals
(
    id        BIGINT      NOT NULL PRIMARY KEY,
    book_id   BIGINT      NOT NULL,
    user_id   BIGINT      NOT NULL,
    status    VARCHAR(50) NOT NULL,
    dueDate   DATE        NOT NULL,
    createdAt TIMESTAMP   NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

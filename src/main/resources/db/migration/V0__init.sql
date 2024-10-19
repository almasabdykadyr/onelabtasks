CREATE TABLE author
(
    id        BIGINT       NOT NULL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP    NOT NULL
);

CREATE TABLE book
(
    id           BIGINT    NOT NULL PRIMARY KEY,
    isbn         VARCHAR(17),
    title        VARCHAR(255),
    description  VARCHAR(500),
    author_id    BIGINT    NOT NULL,
    published_at DATE,
    created_at   TIMESTAMP NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE "user"
(
    id        BIGINT       NOT NULL PRIMARY KEY,
    email     VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName  VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP    NOT NULL
);

CREATE TABLE rental
(
    id        BIGINT      NOT NULL PRIMARY KEY,
    book_id   BIGINT      NOT NULL,
    user_id   BIGINT      NOT NULL,
    status    VARCHAR(50) NOT NULL,
    dueDate   DATE        NOT NULL,
    createdAt TIMESTAMP   NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);

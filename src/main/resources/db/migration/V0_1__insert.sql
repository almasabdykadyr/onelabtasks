-- Insert data into authors
INSERT INTO authors(firstname, lastname, created_at)
VALUES ('George', 'Orwell', current_timestamp),
       ('J.K.', 'Rowling', current_timestamp),
       ('Haruki', 'Murakami', current_timestamp),
       ('F. Scott', 'Fitzgerald', current_timestamp),
       ('Leo', 'Tolstoy', current_timestamp);

-- Insert data into books
INSERT INTO books(isbn, title, description, author_id, published_at, created_at)
VALUES ( '978-0451524935', '1984', 'Dystopian novel set in a totalitarian regime.', 1, '1949-06-08',
        current_timestamp),
       ( '978-0439139601', 'Harry Potter and the Goblet of Fire', 'Fantasy novel about a wizard tournament.', 2,
        '2000-07-08', current_timestamp),
       ( '978-0375718946', 'Norwegian Wood', 'A nostalgic love story.', 3, '1987-09-04', current_timestamp);

-- Insert data into users
INSERT INTO users(email, password, firstname, lastname, created_at)
VALUES ('john.doe@example.com', 'password123', 'John', 'Doe', current_timestamp),
       ('jane.smith@example.com', 'securePass!', 'Jane', 'Smith', current_timestamp),
       ('alice.jones@example.com', 'qwerty123', 'Alice', 'Jones', current_timestamp),
       ('bob.brown@example.com', '123456789', 'Bob', 'Brown', current_timestamp);
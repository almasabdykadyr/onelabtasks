-- Insert data into authors
INSERT INTO authors
VALUES (0, 'George', 'Orwell', current_timestamp),
       (1, 'J.K.', 'Rowling', current_timestamp),
       (2, 'Haruki', 'Murakami', current_timestamp),
       (3, 'F. Scott', 'Fitzgerald', current_timestamp),
       (4, 'Leo', 'Tolstoy', current_timestamp);

-- Insert data into books
INSERT INTO books
VALUES (0, '978-0451524935', '1984', 'Dystopian novel set in a totalitarian regime.', 0, '1949-06-08',
        current_timestamp),
       (1, '978-0439139601', 'Harry Potter and the Goblet of Fire', 'Fantasy novel about a wizard tournament.', 1,
        '2000-07-08', current_timestamp),
       (2, '978-0375718946', 'Norwegian Wood', 'A nostalgic love story.', 2, '1987-09-04', current_timestamp);

-- Insert data into users
INSERT INTO users
VALUES ('john.doe@example.com', 'password123', 'John', 'Doe', current_timestamp),
       ('jane.smith@example.com', 'securePass!', 'Jane', 'Smith', current_timestamp),
       ('alice.jones@example.com', 'qwerty123', 'Alice', 'Jones', current_timestamp),
       ('bob.brown@example.com', '123456789', 'Bob', 'Brown', current_timestamp);
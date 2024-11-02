CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    roles   VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO user_roles
VALUES (1, 'USER'),
       (2, 'USER'),
       (3, 'USER'),
       (4, 'USER');

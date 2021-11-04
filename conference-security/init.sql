CREATE TABLE users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username on authorities (username,authority);

INSERT INTO users (username, password, enabled)
VALUES('user','$2a$10$PwLlfto2SjmfEqruxwQ3AON3PppXx/KzPldGY8Hm9DMwTy5JY2zr6',1);

INSERT INTO authorities (username, authority)
VALUES('user', 'ROLE_USER');

COMMIT;

ALTER TABLE users
ADD nickname VARCHAR(50);

COMMIT;
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
VALUES('Steven','$2a$10$PwLlfto2SjmfEqruxwQ3AON3PppXx/KzPldGY8Hm9DMwTy5JY2zr6',1);

INSERT INTO authorities (username, authority)
VALUES('Steven', 'ROLE_USER');

COMMIT;

ALTER TABLE users
ADD nickname VARCHAR(50);

COMMIT;

CREATE TABLE persistent_logins(
    username VARCHAR(50) NOT NULL,
    series VARCHAR(64) PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);

COMMIT;

CREATE TABLE accounts(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

COMMIT;

CREATE TABLE verification_tokens (
    username VARCHAR(50) NOT NULL,
    token VARCHAR(100) NOT NULL,
    expiry_date DATETIME NOT NULL,
    PRIMARY KEY (username, token)
);

COMMIT;


CREATE TABLE clients
(
    id   UUID PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE contacts
(
    id        UUID PRIMARY KEY,
    client_id UUID         NOT NULL,
    contact   VARCHAR(128) NOT NULL UNIQUE,
    type      VARCHAR(50)  NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients (id)
);
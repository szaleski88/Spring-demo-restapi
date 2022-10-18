--liquibase formatted sql
--changeset szaleski:4
CREATE TABLE users
(
   id BIGINT PRIMARY KEY,
   username VARCHAR(50)  NOT NULL UNIQUE,
   password VARCHAR(100) NOT NULL,
   enabled  BOOLEAN   NOT NULL
 );
--changeset szaleski:5
CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    constraint fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);
create index username_authority on authorities(username, authority);
--changeset szaleski:6
INSERT INTO users(id, username,password, enabled) values (1, 'test', '{bcrypt}$2a$10$7nzH.3EWw43itMHOLoZnzug2Ap5mY5oBgCM5.cPnpYlApzLM3rY0C', true);
INSERT INTO authorities(username, authority) values ('test', 'USER');
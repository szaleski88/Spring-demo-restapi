--liquibase formatted sql
--changeset szaleski:1

CREATE TABLE POST (
    id SERIAL PRIMARY KEY,
    title VARCHAR(400) NOT NULL,
    content VARCHAR(2000) NULL,
    created TIMESTAMP

);
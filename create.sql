CREATE DATABASE pc_parts;
\c pc_parts;
CREATE TABLE parts(id SERIAL PRIMARY KEY, manufacturer VARCHAR, series VARCHAR, price DECIMAL);
CREATE TABLE types(id SERIAL PRIMARY KEY, name VARCHAR);
CREATE TABLE reviews(id SERIAL PRIMARY KEY, writtenby VARCHAR, rating VARCHAR, content VARCHAR, partid INTEGER, createdat BIGINT);
CREATE TABLE parts_types(id SERIAL PRIMARY KEY, partid INTEGER, typeid INTEGER);
CREATE DATABASE pc_parts_test WITH TEMPLATE pc_parts;
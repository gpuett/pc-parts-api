CREATE DATABASE pc_parts;
\c pc_parts;
CREATE TABLE cpus(id SERIAL PRIMARY KEY, manufacturer VARCHAR, series VARCHAR, speed VARCHAR, cores INTEGER, price VARCHAR);
CREATE TABLE motherboards(id SERIAL PRIMARY KEY, manufacturer VARCHAR, ram_slots INTEGER, max_ram VARCHAR, price VARCHAR);
CREATE TABLE storage(id SERIAL PRIMARY KEY, manufacturer VARCHAR, series VARCHAR, type VARCHAR, capacity VARCHAR, price VARCHAR);
CREATE TABLE rams(id SERIAL PRIMARY KEY, manufacturer VARCHAR, speed VARCHAR, size VARCHAR, price VARCHAR);
CREATE TABLE rams(id SERIAL PRIMARY KEY, manufacturer VARCHAR, chipset VARCHAR, memory VARCHAR, price VARCHAR);
CREATE TABLE reviews(id SERIAL PRIMARY KEY, writtenby VARCHAR, rating VARCHAR, content VARCHAR, restaurantid INTEGER, createdat BIGINT);

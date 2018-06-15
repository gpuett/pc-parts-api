CREATE DATABASE pc_parts;
\c pc_parts;
CREATE TABLE cpus(id SERIAL PRIMARY KEY, manufacturer VARCHAR, series VARCHAR, speed VARCHAR, cores INTEGER, price DECIMAL);
CREATE TABLE motherboards(id SERIAL PRIMARY KEY, manufacturer VARCHAR, ramslots INTEGER, maxram VARCHAR, price DECIMAL);
CREATE TABLE storage(id SERIAL PRIMARY KEY, manufacturer VARCHAR, series VARCHAR, type VARCHAR, capacity VARCHAR, price DECIMAL);
CREATE TABLE rams(id SERIAL PRIMARY KEY, manufacturer VARCHAR, speed VARCHAR, size VARCHAR, price DECIMAL);
CREATE TABLE gpus(id SERIAL PRIMARY KEY, manufacturer VARCHAR, chipset VARCHAR, memory VARCHAR, price DECIMAL);
CREATE TABLE reviews(id SERIAL PRIMARY KEY, writtenby VARCHAR, rating VARCHAR, content VARCHAR, cpuid INTEGER, motherboardid INTEGER, storageid INTEGER, ramid INTEGER, gpuid INTEGER, createdat BIGINT);
CREATE DATABASE pc_parts_test WITH TEMPLATE pc_parts;
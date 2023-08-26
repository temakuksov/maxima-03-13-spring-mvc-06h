-- 1. Create database first_db
--CREATE DATABASE first_db
--    WITH
--    OWNER = postgres
--    ENCODING = 'UTF8';

-- 2. Drop table if exist
drop table if exists person;

-- 3. Create person table
create table person (
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(30) NOT NULL,
    surname varchar(40) NOT NULL,
    age int NOT NULL, check (age > 16),
    email varchar(40) NOT NULL UNIQUE
);

-- 4. insert data into person table
insert into person (name, surname, age, email) values
    ('Ivan','Abramov', '33', 'ivan@mail.ru'),
    ('Alexey','Sherbakov', '30', 'alex@mail.ru'),
    ('Stas','Starovoytov', '43', 'stas@mail.ru'),
    ('Ruslan','Belyi', '50', 'ruslan@mail.ru');

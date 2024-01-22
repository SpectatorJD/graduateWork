-- liquibase formatted sql
-- changeset denny:create table users

 create table users(
 id integer generated by default as identity primary key,
 first_name varchar,
 last_name varchar,
 email text,
 phone text,
 password varchar,
 role text,
 image_id integer references image(id)
 );
-- liquibase formatted sql
-- changeset denny:create table comments

 create table comments(
 id integer generated by default as identity primary key,
 text varchar,
 create_at timestamp,
 ads_id integer references ads(id),
 users_id integer references users(id)
 );
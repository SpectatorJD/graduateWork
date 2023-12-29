-- liquibase formatted sql
-- changeset denny:1

 create table ads(
 id bigint generated by default as identity primary key,
 author serial,
 image bytea,
 pk serial,
 price numeric(0,10000000),
 title char(4,32),
 description char(8,64),
 author_first_name char(8,64),
 author_last_name char(8,64),
 email text,
 phone varchar(\+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2})
 );

 create table comments(
 id bigint generated by default as identity primary key,
 commentId integer,
 author integer references ads(id),
 author_image bytea,
 author_first_name char(3,10),
 create_at timestamp,
 pk integer references ads(id),
 text char(8,64),
 adId integer references ads(id)
 );

 create table users(
 id bigint generated by default as identity primary key,
 emile text,
 first_name char(3,10),
 last_name char(3,10),
 phone varchar(\+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2}),
 role text, --role
 image bytea,
 currentPassword char(8,16),
 newPassword char(8,16)
 username char(4,32)
 );

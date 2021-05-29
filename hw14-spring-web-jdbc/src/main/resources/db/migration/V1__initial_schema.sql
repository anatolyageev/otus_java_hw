drop table if exists client;
drop table if exists addressdataset;
drop table if exists phone_data;

create table client
(
    id       bigserial not null primary key,
    login    varchar(255),
    name     varchar(255),
    password varchar(255)
);

create table addressdataset
(
    id        bigserial not null primary key,
    street    varchar(255),
    client_id bigint    not null references client (id)
);

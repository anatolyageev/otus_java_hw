create table phone_data
(
    id        bigserial not null primary key,
    number    varchar(255),
    client_id int8      not null references client (id)
);
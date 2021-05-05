create sequence hibernate_sequence start with 4 increment by 1;

create table addressdataset
(
    id     bigint not null
        constraint addressdataset_pkey
        primary key,
    street varchar(255)
);

alter table addressdataset
    owner to usr;

create table client
(
    id         bigint not null
        constraint client_pkey
        primary key,
    login      varchar(255),
    name       varchar(255),
    password   varchar(255),
    address_id bigint
        constraint fkog7sfrdbcg0xfm4xwkd6ptbqw
        references addressdataset
);

alter table client
    owner to usr;

create table phonedataset
(
    id        bigint not null
        constraint phonedataset_pkey
        primary key,
    number    varchar(255),
    client_id bigint
        constraint fkcxs5aa19muuheshhr7l3wctk0
        references client
);

alter table phonedataset
    owner to usr;



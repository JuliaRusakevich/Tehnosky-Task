--liquibase formatted sql

--changeset julia:1
create schema if not exists accounts;

--changeset julia:2
create table if not exists accounts.users
(
    id              serial primary key,
    full_name       varchar(128)        not null,
    mail            varchar(128) unique not null,
    password        varchar(256)        not null,
    document_type   varchar(128),
    document_number varchar(128) unique,
    status          boolean             not null
);
--rollback  drop table accounts.users;

--changeset julia:3
create table if not exists accounts.account_numbers
(
    id             serial primary key,
    account_number varchar(128) not null unique,
    account_state  decimal default 0.00,
    currency       varchar(3),
    user_id        integer references accounts.users (id)
);
--rollback  drop table accounts.account_numbers;


--changeset julia:4
create table if not exists accounts.transactions
(
    id                  serial primary key,
    transaction_type    varchar(128) not null,
    sum                 decimal      not null,
    account_id   integer references accounts.account_numbers (id),
    createdAt           timestamp,
    createdBy           varchar(128)
);
--rollback  drop table accounts.transactions;
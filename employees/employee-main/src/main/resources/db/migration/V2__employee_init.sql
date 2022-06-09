create schema if not exists employee;

create table if not exists employee.documents(
    id bigserial primary key,
    name text,
    bytes bytea,
    date_format text
);

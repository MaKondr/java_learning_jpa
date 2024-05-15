create table department
(
    department_id integer generated always as identity
        primary key,
    name          varchar(60) not null,
    amount_staff  integer     not null,
    rooms         varchar(60),
    ceo_id        integer
        unique
                              references staff
                                  on delete set null
);

alter table department
    owner to postgres;


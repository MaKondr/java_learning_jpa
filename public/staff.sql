create table staff
(
    staff_id      integer generated always as identity
        primary key,
    name          varchar(100) not null,
    address       varchar(60),
    age           integer
        constraint staff_age_check
            check (age > 17),
    position      varchar(30)  not null,
    department_id integer      not null
        constraint staff_department_department_id_fk
            references department
);

alter table staff
    owner to postgres;


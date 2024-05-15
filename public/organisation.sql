create table organisation
(
    organisation_id integer generated always as identity
        primary key,
    name            varchar(100) not null
        unique,
    address         varchar(60)  not null,
    ceo_id          integer
        references staff
);

alter table organisation
    owner to postgres;


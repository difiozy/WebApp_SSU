create table t_companies
(
    company_id         serial
        primary key,
    company_short_name varchar,
    company_full_name  varchar,
    inn                varchar
);

alter table t_companies
    owner to "user";


create table t_user
(
    sso   varchar not null
        constraint t_user_pk
            primary key,
    name  varchar,
    token varchar
);

alter table t_user
    owner to "user";


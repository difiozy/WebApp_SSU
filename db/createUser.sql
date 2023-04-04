create table t_user
(
    user_id integer not null
        constraint t_user_pk
            primary key,
    sso     varchar not null
        constraint t_user_sso_uq
            unique,
    name    varchar
);

alter table t_user
    owner to "user";


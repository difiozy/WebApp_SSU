create table t_user
(
    sso             varchar not null
        constraint t_user_pk
            unique,
    name            varchar,
    token           varchar,
    dt_expire_token timestamp default (now() + '01:00:00'::interval)
);

alter table t_user
    owner to "user";

CREATE FUNCTION triggerOnUpdateUser()
    RETURNS TRIGGER AS'
    BEGIN
    IF NEW.token IS NOT NULL AND NEW.dt_expire_token is null
    THEN
        NEW.dt_expire_token := (now() + ''01:00:00''::interval);
    END IF;
    RETURN NEW;
    END' LANGUAGE 'plpgsql';


create trigger usertrigger
    before update
    on t_user
    for each row
execute procedure triggeronupdateuser();


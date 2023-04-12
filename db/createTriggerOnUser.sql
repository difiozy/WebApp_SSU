
CREATE FUNCTION triggerOnUpdateUser()
    RETURNS TRIGGER AS'
    BEGIN
    IF NEW.token IS NOT NULL AND NEW.dt_expire_token is null
    THEN
        NEW.dt_expire_token := (now() + ''01:00:00''::interval);
    END IF;
    RETURN NEW;
    END' LANGUAGE 'plpgsql';

commit ;

CREATE TRIGGER userTrigger
    BEFORE UPDATE ON t_user
    FOR EACH ROW
EXECUTE PROCEDURE triggerOnUpdateUser();
commit;
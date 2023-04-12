

commit ;

CREATE TRIGGER userTrigger
    BEFORE UPDATE ON t_user
    FOR EACH ROW
EXECUTE PROCEDURE triggerOnUpdateUser();
commit;
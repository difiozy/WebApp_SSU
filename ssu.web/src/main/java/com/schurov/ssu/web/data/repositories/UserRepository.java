package com.schurov.ssu.web.data.repositories;

import com.schurov.ssu.web.data.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findBySso(String sso);

    @Query("""
            select sso from t_user where dt_expire_token < now()
            fetch first 1000 rows only
            """)
    List<String> getExpireTokenSso();

    @Query("""
            with updt as (
            update t_user
            set token = null,
            dt_expire_token = null
            where sso in (:sso)
            returning 1
            )
            select count(*) from updt
            """)
    Integer updateUsersBySso(List<String> sso);
}

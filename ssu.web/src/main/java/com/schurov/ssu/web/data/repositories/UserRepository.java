package com.schurov.ssu.web.data.repositories;

import com.schurov.ssu.web.data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findBySso(String sso);
}

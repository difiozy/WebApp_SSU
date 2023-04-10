package com.schurov.ssu.web.data.cache;

import com.schurov.ssu.web.data.model.UserCacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCacheRepository extends CrudRepository<UserCacheable, String> {
}

package com.schurov.ssu.web.data.repositories;

import com.schurov.ssu.web.data.model.Setting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends CrudRepository<Setting, String> {
    Setting findByName(String name);
}

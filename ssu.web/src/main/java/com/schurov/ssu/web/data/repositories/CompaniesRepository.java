package com.schurov.ssu.web.data.repositories;

import com.schurov.ssu.web.data.model.Companies;
import org.springframework.data.repository.CrudRepository;

public interface CompaniesRepository  extends CrudRepository<Companies, Long> {
}

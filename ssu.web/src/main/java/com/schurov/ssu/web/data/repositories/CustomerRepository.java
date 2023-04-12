package com.schurov.ssu.web.data.repositories;

import com.schurov.ssu.web.data.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}

package com.schurov.ssu.web.service;

import com.schurov.ssu.web.data.model.Customer;
import com.schurov.ssu.web.data.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepositor;

    public CustomerService(CustomerRepository customerRepositor) {
        this.customerRepositor = customerRepositor;
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepositor.findAll();
    }
}

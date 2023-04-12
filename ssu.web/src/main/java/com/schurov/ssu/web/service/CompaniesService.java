package com.schurov.ssu.web.service;

import com.schurov.ssu.web.data.model.Companies;
import com.schurov.ssu.web.data.repositories.CompaniesRepository;
import org.springframework.stereotype.Service;

@Service
public class CompaniesService {
    private final CompaniesRepository companiesRepository;

    public CompaniesService(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }


    public Iterable<Companies> getAllCompanies() {
        return companiesRepository.findAll();
    }
}

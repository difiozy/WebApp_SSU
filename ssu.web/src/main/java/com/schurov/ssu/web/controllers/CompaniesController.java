package com.schurov.ssu.web.controllers;

import com.schurov.ssu.web.service.CompaniesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompaniesController {
    private final CompaniesService companiesService;

    public CompaniesController(CompaniesService companiesService) {
        this.companiesService = companiesService;
    }

    @GetMapping("/company/all")
    public String getAllCompany(Model model) {
        model.addAttribute("companies", companiesService.getAllCompanies());
        return "companies";
    }
}

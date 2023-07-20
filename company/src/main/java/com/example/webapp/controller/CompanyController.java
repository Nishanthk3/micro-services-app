package com.example.webapp.controller;

import com.example.webapp.entity.playground.CompanyDO;
import com.example.webapp.repository.playground.CompanyRepository;
import com.example.webapp.service.Company;
import com.example.webapp.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company")
@Slf4j
class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        return ResponseEntity.ok(companyService.getAllCompaines());
    }

    @GetMapping(value = "{id}")
    public Company get(@PathVariable Long id) {
        CompanyDO companyDO = this.companyRepository.findById(id).get();
        return Company.builder().id(companyDO.getId()).companyName(companyDO.getCompanyName()).build();
    }

    @GetMapping(value = "/name")
    public List<Company> getByName(@RequestParam String name) {
        List<CompanyDO> companyDO = (List<CompanyDO>) this.companyRepository.findByCompanyName(name);
        List<Company> companies = new ArrayList<>();
        for (CompanyDO compDo : companyDO) {
            companies.add(Company.builder().id(compDo.getId()).companyName(compDo.getCompanyName()).build());
        }
        return companies;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Company create(@RequestBody Company company) {
        return companyService.create(company);
    }
}
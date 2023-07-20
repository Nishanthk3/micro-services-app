package com.example.webapp.service;

import com.example.webapp.entity.playground.CompanyDO;
import com.example.webapp.repository.playground.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompaines() {
        List<CompanyDO> companyDO = this.companyRepository.findAll();
        List<Company> companies = new ArrayList<>();
        for (CompanyDO empDo : companyDO) {
            companies.add(Company.builder().id(empDo.getId()).companyName(empDo.getCompanyName()).build());
        }
        log.info("All Companies: {}", companies);
        log.info("Company here:  {}", companies.get(0));
        return companies;
    }

    public Company create(Company company) {
        System.out.println(company.toString());
        CompanyDO companyDo = this.companyRepository.save(CompanyDO.builder().companyName(company.getCompanyName()).build());
        return Company.builder().id(companyDo.getId()).companyName(companyDo.getCompanyName()).build();
    }
}

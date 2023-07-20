package com.example.webapp.graphql;

import com.example.webapp.service.Company;
import com.example.webapp.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class CompanyGraphQLController {

    @Autowired
    private CompanyService companyService;

    @QueryMapping
    Iterable<Company> companies() {
        log.info("[GraphQL] Fetching all companies");
        return companyService.getAllCompaines();
    }

//    @QueryMapping
//    public Employee employeeById(@Argument Long id) {
//        return employeeService.findById(id);
//    }
//
    @MutationMapping
    public Company addCompany(@Argument CompanyInput company) {
        Company companyDto = Company.builder().companyName(company.companyName).build();
        return companyService.create(companyDto);
    }

    record CompanyInput(String companyName) {
    }
}

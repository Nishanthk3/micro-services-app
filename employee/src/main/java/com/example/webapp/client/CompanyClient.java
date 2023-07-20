package com.example.webapp.client;

import com.example.webapp.service.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "company-service", url = "${application.config.company-url}")
public interface CompanyClient {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Company> findAllCompanies();
}

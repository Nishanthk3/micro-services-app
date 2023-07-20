package com.example.webapp.repository.playground;

import com.example.webapp.entity.playground.CompanyDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void testEmployee() {
        for (CompanyDO emp : this.companyRepository.findAll()) {
            System.out.println(emp.toString());
        }
    }
}

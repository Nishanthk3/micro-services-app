package com.example.webapp.repository.playground;

import com.example.webapp.entity.playground.EmployeeDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void testEmployee() {
        for (EmployeeDO emp : this.employeeRepository.findAll()) {
            System.out.println("[Employee] ID: " + emp.getId() + ", Name: " + emp.getEmployeeName());
        }
    }
}

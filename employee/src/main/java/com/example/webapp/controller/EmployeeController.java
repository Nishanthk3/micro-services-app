package com.example.webapp.controller;

import com.example.webapp.service.Company;
import com.example.webapp.service.Employee;
import com.example.webapp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        log.info("Fetching all employees");
        return employeeService.findAllEmployees();
    }

    @GetMapping(value = "{id}")
    public Employee get(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping(value = "/name")
    public List<Employee> getByName(@RequestParam String name) {
        return employeeService.getByName(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long addEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PostMapping(value = "company", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Company addCompany(@RequestBody String companyName) {
        return employeeService.addCompany(companyName);
    }
}
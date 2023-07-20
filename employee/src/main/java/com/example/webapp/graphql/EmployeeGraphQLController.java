package com.example.webapp.graphql;

import com.example.webapp.service.Employee;
import com.example.webapp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class EmployeeGraphQLController {

    @Autowired
    private EmployeeService employeeService;

    @QueryMapping
    Iterable<Employee> employees() {
        log.info("[GraphQL] Fetching all employees");
        return employeeService.findAllEmployees();
    }

    @QueryMapping /** not working yet */
    Iterable<Employee> employeesGraphQL() {
        log.info("[GraphQL] Fetching all employees");
        return employeeService.findAllEmployeesGraphQL();
    }

    @QueryMapping
    public Employee employeeById(@Argument Long id) {
        return employeeService.findById(id);
    }

    @MutationMapping
    public Employee addEmployee(@Argument EmployeeInput employee) {
        Employee emp = Employee.builder().employeeName(employee.employeeName).build();
        return employeeService.createEmployee(emp);
    }

    record EmployeeInput(String employeeName) {
    }
}

package com.example.webapp.service;

import com.example.webapp.client.CompanyClient;
import com.example.webapp.entity.playground.EmployeeDO;
import com.example.webapp.repository.playground.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    //@Autowired
    private EmployeeRepository employeeRepository;
    //@Autowired
    private CompanyClient companyClient;

    // Constructor Autowiring with using @Autowired annotation
    public EmployeeService(EmployeeRepository repository, CompanyClient companyClient) {
        this.employeeRepository = repository;
        this.companyClient = companyClient;
    }

    private HttpGraphQlClient httpGraphQlClient;
    private DozerBeanMapper mapper = new DozerBeanMapper();
    public static String GET_COMPANIES_DOC = """
            query Companies {
                companies {
                    id
                    companyName
                }
            }
                            
            """;
    public static String CREATE_COMPANY_DOC =
//            query TvSeries($nameQuery: String, $type: String) {\n"
//            + "  Entities(query: $nameQuery, _type: $type, count: 100) {" +
            """
                        mutation AddCompany($compName: String) {
                        addCompany(company: {companyName: $compName}) {
                            id
                            companyName
                        }
                    }
                    """;

    @PostConstruct
    public void instantiateHttpGraphQlClient() {
        if (httpGraphQlClient == null) {
            WebClient client = WebClient.builder().baseUrl("http://localhost:8081/graphql").build();
            httpGraphQlClient = HttpGraphQlClient.builder(client).build();
        }
    }

    public List<Employee> findAllEmployees() {
        List<EmployeeDO> employeeDO = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDO empDo : employeeDO) {
            employees.add(Employee.builder().id(empDo.getId()).employeeName(empDo.getEmployeeName()).build());
        }
        List<Company> companies = companyClient.findAllCompanies();
        Company company = companies.get(0);
        employees.forEach(i -> i.setCompanyName(company.getCompanyName()));
        return employees;
    }

    public List<Employee> findAllEmployeesGraphQL() {
        List<EmployeeDO> employeeDO = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDO empDo : employeeDO) {
            employees.add(Employee.builder().id(empDo.getId()).employeeName(empDo.getEmployeeName()).build());
        }
        List<Company> companies = httpGraphQlClient.document(GET_COMPANIES_DOC).retrieve("companies").toEntityList(Company.class).block();
        Company company = companies.get(0);
        employees.forEach(i -> i.setCompanyName(company.getCompanyName()));
        return employees;
    }

    public Employee findById(Long id) {
        EmployeeDO empDo = employeeRepository.findById(id).get();
        return Employee.builder().id(empDo.getId()).employeeName(empDo.getEmployeeName()).build();
    }

    public List<Employee> getByName(String name) {
        List<EmployeeDO> employeeDO = (List<EmployeeDO>) employeeRepository.findByEmployeeName(name);
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDO empDo : employeeDO) {
            employees.add(Employee.builder().id(empDo.getId()).employeeName(empDo.getEmployeeName()).build());
        }
        return employees;
    }

    public Long create(Employee employee) {
        EmployeeDO employeeDO = addEmployee(employee);
        return employeeDO.getId();
    }

    public Employee createEmployee(Employee employee) {
        return mapper.map(addEmployee(employee), Employee.class);
    }

    private EmployeeDO addEmployee(Employee employee) {
        EmployeeDO employeeDO = employeeRepository.save(EmployeeDO.builder().employeeName(employee.getEmployeeName()).build());
        return employeeDO;
    }

    public Company addCompany(String companyName) {
        log.info("Company name to be inserted : {}", companyName);
        List<Company> companies = httpGraphQlClient.document(CREATE_COMPANY_DOC).attribute("compName", "N Comp").retrieve("addCompany").toEntityList(Company.class).block();
        return companies.get(0);
    }

}

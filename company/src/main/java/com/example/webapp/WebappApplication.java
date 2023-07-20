package com.example.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebappApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebappApplication.class, args);
//
//        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
//        employeeRepository.save( EmployeeDO.builder().employeeName("testname").build());
    }
}


//@Component
//class CommandLine implements CommandLineRunner {
//
//    @Autowired
//    EmployeeRepository employeeRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        for (Employee emp : this.employeeRepository.findAll()) {
//            System.out.println(emp.toString());
//        }
//    }
//}
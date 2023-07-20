package com.example.webapp.controller;

import com.example.webapp.service.Author;
import com.example.webapp.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Slf4j
class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

//
//    @GetMapping(value = "{id}")
//    public Employee get(@PathVariable Long id) {
//        return employeeService.findById(id);
//    }
//
//    @GetMapping(value = "/name")
//    public List<Employee> getByName(@RequestParam String name) {
//        return employeeService.getByName(name);
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Long addEmployee(@RequestBody Employee employee) {
//        return employeeService.create(employee);
//    }
}
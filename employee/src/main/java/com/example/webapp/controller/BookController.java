package com.example.webapp.controller;

import com.example.webapp.service.Book;
import com.example.webapp.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        log.info("Fetching all books");
        return bookService.getAllBooks();
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
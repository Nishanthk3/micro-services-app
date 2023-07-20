package com.example.webapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index(@RequestParam(required = false) String text) {
        if (text != null && text.trim().length() > 0) {
            return text;
        }
        return "Welcome to Employee Spring Boot Application";
    }
}

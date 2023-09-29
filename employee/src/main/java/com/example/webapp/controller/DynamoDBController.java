package com.example.webapp.controller;

import com.example.webapp.dynamodb.entity.TestDaoImpl;
import com.example.webapp.dynamodb.service.DynamoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dynamo")
public class DynamoDBController {

    @Autowired
    private DynamoDBService dynamoDBService;

    @Autowired
    private TestDaoImpl testDao;

    @GetMapping(path = "/tables")
    public List<String> dynamoDbClient() {
        return dynamoDBService.getTables();
    }
//
//    @GetMapping(path = "/dynamo/test")
//    public void createTestTable() {
//        TestDO testDO = TestDO.builder().id(UUID.randomUUID().toString()).email("abc@gmail.com").name("Dynamo Db Test").age(5).profession("No Sql Db").build();
//        testDao.put(testDO);
//    }

    @GetMapping(path = "/test/{id}")
    public void getTestDOById(@PathVariable(required = true) String id) {
        testDao.get(id);
    }
}

package com.example.webapp.controller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.webapp.dynamodb.entity.Product;
import com.example.webapp.dynamodb.entity.TestDao;
import com.example.webapp.dynamodb.service.DynamoDBService;
import com.example.webapp.dynamodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dynamo")
public class DynamoDBController {

    @Autowired
    private DynamoDBService dynamoDBService;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private TestDao testDao;

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/tables")
    public List<String> dynamoDbClient() {
        return dynamoDBService.getTables();
    }

    @GetMapping(path = "/product/all")
    public List<Product> getProduct() {
        return productService.getAll();
    }

    @GetMapping(path = "/product/{id}/{name}")
    public Product getProduct(@PathVariable String id, @PathVariable(required = true) String name) {
        return productService.get(id, name);
    }

    @PostMapping(path = "/product")
    public void createProduct(@RequestBody Product product) {
        productService.create(product);
    }

    @PutMapping(path = "/product/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping(path = "/product/{id}/{name}")
    public boolean deleteProduct(@PathVariable String id, @PathVariable(required = true) String name) {
        return productService.delete(id, name);
    }

    @GetMapping(path = "/test/{id}")
    public void getTestDOById(@PathVariable String id) {
        testDao.get(id);
    }
}

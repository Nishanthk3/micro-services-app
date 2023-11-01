package com.example.webapp.dynamodb.service;

import com.example.webapp.dynamodb.entity.Product;

import java.util.List;

public interface ProductService {
    void create(Product product);
    Product getByPKAndSK(String id, String name);
    List<Product> getById(String id);
    List<Product> getAll();
    Product update(String id, Product product);
    boolean delete(String id, String name);
}

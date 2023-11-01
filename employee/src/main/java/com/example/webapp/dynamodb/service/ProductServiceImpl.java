package com.example.webapp.dynamodb.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.webapp.dynamodb.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public void create(Product product) {
        dynamoDBMapper.save(product);
    }

    @Override
    public Product getByPKAndSK(String id, String name) {
        return dynamoDBMapper.load(Product.class, id, name);
    }

    @Override
    public List<Product> getById(String id) {
        Product product = new Product();
        product.setProductId(id);
        DynamoDBQueryExpression<Product> queryExpression = new DynamoDBQueryExpression<Product>()
                .withHashKeyValues(product);
        List<Product> myCollection = dynamoDBMapper.query(Product.class, queryExpression);
        return myCollection;
    }

    @Override
    public List<Product> getAll() {
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        expression.addFilterCondition("productId", new Condition().withComparisonOperator(ComparisonOperator.NOT_NULL));
        return dynamoDBMapper.scan(Product.class, expression);
    }

    @Override
    public Product update(String id, Product product) {
        dynamoDBMapper.save(product, new DynamoDBSaveExpression().withExpectedEntry("productId", new ExpectedAttributeValue(
                new AttributeValue().withS(id)
        )));
        return getByPKAndSK(id, product.getProductName());
    }

    @Override
    public boolean delete(String id, String name) {
        Product product = getByPKAndSK(id, name);
        dynamoDBMapper.delete(product);
        return true;
    }
}

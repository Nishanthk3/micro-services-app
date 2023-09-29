package com.example.webapp.dynamodb.service;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;

public interface DynamoDBService {

    DynamoDbClient getDynamoDbClient();
    List<String> getTables();
}

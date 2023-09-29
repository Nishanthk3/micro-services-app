package com.example.webapp.dynamodb.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;

@Service
public class DynamoDbServiceImpl implements DynamoDBService {

    @Autowired
    private DynamoDBManager dynamoDBClient;

    private DynamoDbClient client;

    @PostConstruct
    public void init() {
        client = dynamoDBClient.getClient();
    }

    @Override
    public DynamoDbClient getDynamoDbClient() {
        return client;
    }
    @Override
    public List<String> getTables() {
        ListTablesResponse listTablesResponse = client.listTables();
        List<String> tables = listTablesResponse.tableNames();
        for (String table : tables) {
            System.out.println(table);
        }
        return tables;
    }
}

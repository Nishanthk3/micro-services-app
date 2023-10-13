package com.example.webapp.dynamodb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;

@Service
@Slf4j
public class DynamoDbServiceImpl implements DynamoDBService {

    @Autowired
    private DynamoDbClient client;

    @Override
    public List<String> getTables() {
        ListTablesResponse listTablesResponse = client.listTables();
        List<String> tables = listTablesResponse.tableNames();
        log.info("Dynamo DB Tables: {} ", tables);
        return tables;
    }
}

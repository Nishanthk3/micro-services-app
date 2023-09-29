package com.example.webapp.dynamodb.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Service
public class DynamoDBManager {

    private DynamoDbClient client;

    @PostConstruct
    public void init() {

        client = DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:9094"))
                // The region is meaningless for local DynamoDb but required for client builder validation
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("DUMMYIDEXAMPLE", "DUMMYEXAMPLEKEY")))
                .build();
        System.out.println("DynamoDB Client: " + client);
    }

    public DynamoDbClient getClient() {
        return client;
    }
}

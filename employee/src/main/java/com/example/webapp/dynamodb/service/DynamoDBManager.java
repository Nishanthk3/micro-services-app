package com.example.webapp.dynamodb.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Service
@Slf4j
public class DynamoDBManager {

    private static final String DUMMY_ID = "DUMMYIDEXAMPLE";
    private static final String DUMMY_KEY = "DUMMYEXAMPLEKEY";

    private DynamoDbClient client;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(DUMMY_ID, DUMMY_KEY)
                        )
                )
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:9094", Region.US_EAST_1.id()))
                .build();
    }


    @Bean
    public DynamoDbClient dynamoDbClient() {
        log.info("DynamoDbClient Autowired : {}", client.listTables());
        return client;
    }

    @PostConstruct
    private void init() {
        client = DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:9094"))
                // The region is meaningless for local DynamoDb but required for client builder validation
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(DUMMY_ID, DUMMY_KEY)))
                .build();
        log.info("DynamoDB Client Initialized: {} ", client);
    }
}

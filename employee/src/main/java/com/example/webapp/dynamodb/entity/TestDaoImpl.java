package com.example.webapp.dynamodb.entity;

import com.example.webapp.dynamodb.service.DynamoDBService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class TestDaoImpl implements TestDao {

    private static final String TEST_TABLE_NAME = "test";
    private DynamoDbClient client;

    @Autowired
    private DynamoDBService dynamoDBService;

    @PostConstruct
    public void init() {
        client = dynamoDBService.getDynamoDbClient();
    }

    @Override
    public Mono<Void> put(TestDO test) {
        if (!dynamoDBService.getTables().contains(TEST_TABLE_NAME)) {
            createTable(client, TEST_TABLE_NAME, "id");
        }
        putItemInTable(client, TEST_TABLE_NAME, "id", test.getId(), "email", test.getEmail(), "name",
                test.getName(), "age", test.getAge(), "profession", test.getProfession());
        return null;
    }

    public static void putItemInTable(DynamoDbClient ddb, String tableName, String key, String keyVal, String email,
                                      String emailVal, String name, String nameVal, String age, int ageVal,
                                      String profession, String professionVal) {

        HashMap<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put(key, AttributeValue.builder().s(keyVal).build());
        itemValues.put(email, AttributeValue.builder().s(emailVal).build());
        itemValues.put(name, AttributeValue.builder().s(nameVal).build());
        itemValues.put(age, AttributeValue.builder().n(String.valueOf(ageVal)).build());
        itemValues.put(profession, AttributeValue.builder().s(professionVal).build());

        PutItemRequest request = PutItemRequest.builder().tableName(tableName).item(itemValues).build();

        try {
            PutItemResponse response = ddb.putItem(request);
            System.out.println(tableName + " was successfully updated. The request id is " + response.responseMetadata().requestId());

        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The Amazon DynamoDB table \"%s\" can't be found.\n", tableName);
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
            System.exit(1);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public Mono<TestDO> get(String id) {
        getDynamoDBItem(client, TEST_TABLE_NAME, "id", id);
        return null;
    }

    private void getDynamoDBItem(DynamoDbClient ddb, String tableName, String key, String keyVal) {
        Map<String, AttributeValue> keyToGet = new HashMap<>();
        keyToGet.put(key, AttributeValue.builder().s(keyVal).build());

        GetItemRequest request = GetItemRequest.builder().key(keyToGet).tableName(tableName).build();

        try {
            // If there is no matching item, GetItem does not return any data.
            Map<String, AttributeValue> returnedItem = ddb.getItem(request).item();
            if (returnedItem.isEmpty()) System.out.format("No item found with the key %s!\n", key);
            else {
                Set<String> keys = returnedItem.keySet();
                System.out.println("Amazon DynamoDB table attributes: \n");
                for (String key1 : keys) {
                    System.out.format("%s: %s\n", key1, returnedItem.get(key1).toString());
                }
            }

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private String createTable(DynamoDbClient ddb, String tableName, String key) {
        DynamoDbWaiter dbWaiter = ddb.waiter();
        CreateTableRequest request = CreateTableRequest.builder().attributeDefinitions(AttributeDefinition.builder().attributeName(key).attributeType(ScalarAttributeType.S).build()).keySchema(KeySchemaElement.builder().attributeName(key).keyType(KeyType.HASH).build()).provisionedThroughput(ProvisionedThroughput.builder().readCapacityUnits(10L).writeCapacityUnits(10L).build()).tableName(tableName).build();

        String newTable = "";
        try {
            CreateTableResponse response = ddb.createTable(request);
            DescribeTableRequest tableRequest = DescribeTableRequest.builder().tableName(tableName).build();

            // Wait until the Amazon DynamoDB table is created.
            WaiterResponse<DescribeTableResponse> waiterResponse = dbWaiter.waitUntilTableExists(tableRequest);
            waiterResponse.matched().response().ifPresent(System.out::println);
            newTable = response.tableDescription().tableName();
            return newTable;

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return "";
    }
}
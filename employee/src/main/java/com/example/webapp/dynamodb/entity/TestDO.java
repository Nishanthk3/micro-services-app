package com.example.webapp.dynamodb.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.extensions.annotations.DynamoDbVersionAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Setter
@Getter
@DynamoDbBean
@EqualsAndHashCode
@ToString
@Builder
public class TestDO {
    private String id;
    private String email;
    private String name;
    private int age;
    private String profession;
    private Long version;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    @DynamoDbSortKey
    public String getEmail() {
        return email;
    }

    @DynamoDbVersionAttribute
    public Long getVersion() {
        return version;
    }


}

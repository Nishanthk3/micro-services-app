package com.example.webapp.dynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.Setter;

@DynamoDBDocument
@Setter
@Getter
public class Tag {
    private String key;
    private String value;
}

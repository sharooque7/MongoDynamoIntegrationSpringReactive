package com.hetains.reactive.config.dynamo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import javax.annotation.PostConstruct;


@Configuration
public class DynamoDbTableConfiguration {

    private  final DynamoDbClient dynamoDbClient;

    public DynamoDbTableConfiguration(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Value("${dynamodb.tableName}")
    private String tableName;

    @Value("${dynamodb.readCapacityUnits}")
    private Long readCapacityUnits;

    @Value("${dynamodb.writeCapacityUnits}")
    private Long writeCapacityUnits;

    @PostConstruct
    public void createDynamoDbTable() {
        boolean tableExists = dynamoDbClient.listTables().tableNames().contains(tableName);

        if (tableExists) {
            System.out.println("Table " + tableName + " already exists.");
        } else {
            // Define the table schema
            CreateTableRequest createTableRequest = CreateTableRequest.builder()
                    .tableName("Address")
                    .keySchema(
                            KeySchemaElement.builder().attributeName("country").keyType(KeyType.HASH).build(),
                            KeySchemaElement.builder().attributeName("sortKey").keyType(KeyType.RANGE).build()
                    )
                    .attributeDefinitions(
                            AttributeDefinition.builder().attributeName("country").attributeType(ScalarAttributeType.S).build(), // Define partition key attribute
                            AttributeDefinition.builder().attributeName("sortKey").attributeType(ScalarAttributeType.S).build() // Define sort key attribute
                    )
                    .provisionedThroughput(ProvisionedThroughput.builder().readCapacityUnits(5L).writeCapacityUnits(5L).build())
                    .build();

            dynamoDbClient.createTable(createTableRequest);
        }
    }
}

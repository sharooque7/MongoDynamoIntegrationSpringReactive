package com.hetains.reactive.config.dynamo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.regions.Region;
import java.net.URI;

@Configuration
public class DynamoDBConfiguration {
    String accessKey = "zf8pox";
    String secretKey = "q2tq4j";
    AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
    StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.US_EAST_1) // Since local DynamoDB doesn't require a region, any valid region can be used
                .credentialsProvider(credentialsProvider)
                .endpointOverride(URI.create("http://localhost:9000")) // Specify the local DynamoDB endpoint
                .build();
    }

    @Bean
    public DynamoDbAsyncClient dynamoDbAsyncClient() {
        return DynamoDbAsyncClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .endpointOverride(URI.create("http://localhost:9000")) // Specify the local DynamoDB endpoint
                .build();
    }

    @Bean
    public DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient(DynamoDbAsyncClient asyncClient) {
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(asyncClient)
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }



}

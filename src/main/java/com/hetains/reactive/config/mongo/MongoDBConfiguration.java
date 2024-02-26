package com.hetains.reactive.config.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoDBConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoDBConnectionUri;

    @Bean(name = "mongoClient")
    public MongoClient mongoClient() {
        return MongoClients.create(mongoDBConnectionUri);
    }
}

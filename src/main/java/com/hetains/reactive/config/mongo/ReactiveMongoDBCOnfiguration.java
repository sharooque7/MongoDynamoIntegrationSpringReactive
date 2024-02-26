package com.hetains.reactive.config.mongo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;


@Configuration
public class ReactiveMongoDBCOnfiguration {
    @Value("${spring.data.mongodb.uri}")
    private String mongoDBConnectionUrl;
    @Bean(name = "reactiveMongoClient")
    public MongoClient mongoClient() {
        return MongoClients.create(mongoDBConnectionUrl);
    }

    @Bean(name="reactiveMongoTemplate")
    public ReactiveMongoTemplate reactiveMongoTemplate(@Qualifier("reactiveMongoClient") MongoClient mongoClient) {
        return new ReactiveMongoTemplate(mongoClient, "asset");
    }

//    @Bean(name = "ReactiveMongoClientFactoryBean")
//    public ReactiveMongoClientFactoryBean reactiveMongoClientFactoryBean() {
//        ReactiveMongoClientFactoryBean mongo = new ReactiveMongoClientFactoryBean();
//        mongo.setHost("localhost");
//        return mongo;
//    }
}

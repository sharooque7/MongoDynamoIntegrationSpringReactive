package com.hetains.reactive.config.repository;

import com.hetains.reactive.repositories.address.AddressDynamoRepository;
import com.hetains.reactive.repositories.address.AddressMongoRepository;
import com.hetains.reactive.repositories.address.AddressRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AddressRepositoryConfiguration {

    @Profile("dynamo")
    @ConditionalOnProperty(name = "address.repository.type", havingValue = "dynamo")
    public AddressRepository addressDynamoRepository(AddressDynamoRepository addressDynamoRepository){
        return  addressDynamoRepository;
    }

    @Profile("mongo")
    @ConditionalOnProperty(name="address.repository.type",havingValue = "mongo")
    public AddressMongoRepository addressMongoRepository(AddressMongoRepository addressMongoRepository){
        return  addressMongoRepository;
    }
}

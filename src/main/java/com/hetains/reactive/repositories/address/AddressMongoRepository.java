package com.hetains.reactive.repositories.address;

import com.hetains.reactive.entities.Address;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository("addressMongoRepository")
@Profile("mongo")
public class AddressMongoRepository implements AddressRepository {

    private final ReactiveMongoTemplate mongodbTemplate;

    public AddressMongoRepository(@Qualifier("reactiveMongoTemplate") ReactiveMongoTemplate mongodbTemplate) {
        this.mongodbTemplate = mongodbTemplate;
    }
    @Override
    public Mono<Address> save(Address address) {
        return mongodbTemplate.save(address);
    }
    @Override
    public Mono<Address> findById(String id) {
        return mongodbTemplate.findById(id, Address.class);
    }
    @Override
    public Flux<Address> findAll() {
        return mongodbTemplate.findAll(Address.class);
    }
    @Override
    public Mono<Void> deleteById(String id) {
        return mongodbTemplate.remove(findById(id)).then();
    }
}

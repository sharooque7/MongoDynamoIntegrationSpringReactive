package com.hetains.reactive.repositories.customer;

import com.hetains.reactive.entities.Address;
import com.hetains.reactive.entities.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository("customerDocumentRepository")
@Slf4j
public class CustomerDocumentRepository implements CustomerRepository {

    private final ReactiveMongoTemplate mongodbTemplate;

    public CustomerDocumentRepository(@Qualifier("reactiveDocumentTemplate") ReactiveMongoTemplate mongodbTemplate) {
        this.mongodbTemplate = mongodbTemplate;
    }


    @Override
    public Mono<CustomerEntity> save(CustomerEntity customer) {
        log.info("Repository creating");
        // Handle the error here
        // For example, log the error or throw a custom exception
        return this.mongodbTemplate.save(customer).
        onErrorResume(Mono::error)
                .doOnSuccess(savedCustomer -> {
                    // Perform an action when the save operation succeeds
                    // For example, log a success message
                    System.out.println("Customer saved successfully: " + savedCustomer);
                });
    }

    @Override
    public Mono<CustomerEntity> findById(String id) {
        return mongodbTemplate.findById(id, CustomerEntity.class);
    }
    @Override
    public Flux<CustomerEntity> findAll() {
        return mongodbTemplate.findAll(CustomerEntity.class);
    }
    @Override
    public Mono<Void> deleteById(String id) {
        return mongodbTemplate.remove(findById(id)).then();
    }
}

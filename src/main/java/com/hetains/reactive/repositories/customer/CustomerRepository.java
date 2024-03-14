package com.hetains.reactive.repositories.customer;

import com.hetains.reactive.entities.CustomerEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository  {

    Mono<CustomerEntity> save(CustomerEntity customer);

    Mono<CustomerEntity> findById(String id);

    Flux<CustomerEntity> findAll();

    Mono<Void> deleteById(String id);
}

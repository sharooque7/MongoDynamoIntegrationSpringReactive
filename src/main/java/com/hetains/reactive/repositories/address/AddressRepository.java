package com.hetains.reactive.repositories.address;

import com.hetains.reactive.entities.Address;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressRepository  {

    Mono<Address> save(Address address);

    Mono<Address> findById(String id);

    Flux<Address> findAll();

    Mono<Void> deleteById(String id);
}

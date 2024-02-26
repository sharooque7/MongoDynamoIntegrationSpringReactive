package com.hetains.reactive.repositories.user;

import com.hetains.reactive.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository  {

    Mono<User> save(User user);

    Mono<User> findById(String id);

    Flux<User> findAll();

    Mono<Void> deleteById(String id);
}

package com.hetains.reactive.repositories.user;


import com.hetains.reactive.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserMongoRepository implements UserRepository {

    private final ReactiveMongoTemplate mongodbTemplate;

    public UserMongoRepository(@Qualifier("reactiveMongoTemplate") ReactiveMongoTemplate mongodbTemplate) {
        this.mongodbTemplate = mongodbTemplate;
    }

    @Override
    public Mono<User> save(User user){
        return this.mongodbTemplate.save(user);
    }

    @Override
    public Mono<User> findById(String id) {
        return this.mongodbTemplate.findById(id,User.class);
    }

    @Override
    public Flux<User> findAll() {
        return null;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return null;
    }


}

package com.hetains.reactive.services;

import com.hetains.reactive.entities.User;
import com.hetains.reactive.repositories.user.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Mono<User> save(User user) {
        return  this.userRepository.save(user);
    }

    public Mono<User> get(String id) {
        return  this.userRepository.findById(id);
    }

}
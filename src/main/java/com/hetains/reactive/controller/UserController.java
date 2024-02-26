package com.hetains.reactive.controller;

import com.hetains.reactive.dto.UserWithAddress;
import com.hetains.reactive.entities.User;
import com.hetains.reactive.services.AddressService;
import com.hetains.reactive.services.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public Mono<User> create(@RequestBody User user) {
        return this.userService.save(user);
    }

    @GetMapping("/get/{id}")
    public Mono<UserWithAddress> get(@PathVariable String id) {
        return userService.get(id)
                .flatMap(userDetails -> addressService.findById(userDetails.getAddress())
                        .map(addressDetails -> new UserWithAddress(userDetails, addressDetails)));
    }
}


package com.hetains.reactive.controller;


import com.hetains.reactive.entities.Address;
import com.hetains.reactive.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public Mono<Address> create(@RequestBody Address address) {
        return this.addressService.save(address);
    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<Address>> get(@PathVariable String id) {
        return this.addressService.findById(id)
                .map(address -> ResponseEntity.ok(address))
                .defaultIfEmpty(ResponseEntity.notFound().build()) // Return 404 Not Found if address not found
                .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/get/all")
    public Mono<ResponseEntity<Flux<Address>>> all() {
        return this.addressService.findAll()
                .collectList()
                .map(addresses -> ResponseEntity.ok().body(Flux.fromIterable(addresses)))
                .onErrorResume(error -> {
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Flux.empty()));
                });
    }
}

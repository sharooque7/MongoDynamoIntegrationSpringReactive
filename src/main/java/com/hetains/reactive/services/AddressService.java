package com.hetains.reactive.services;


import com.hetains.reactive.entities.Address;
import com.hetains.reactive.repositories.address.AddressRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AddressService  {

    private  final  AddressRepository addressRepository;

    public  AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public Mono<Address> save(Address address){
        return this.addressRepository.save(address);
    }
    public  Mono<Address> findById(String id){
        return this.addressRepository.findById(id);
    }

    public  Flux<Address> findAll(){
        return this.addressRepository.findAll();
    }



}

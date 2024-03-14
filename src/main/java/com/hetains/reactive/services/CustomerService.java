package com.hetains.reactive.services;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hetains.reactive.dto.CustomerRequest;
import com.hetains.reactive.dto.CustomerResponse;
import com.hetains.reactive.entities.CustomerEntity;
import com.hetains.reactive.repositories.customer.CustomerDocumentRepository;
import com.hetains.reactive.repositories.customer.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {


    private final CustomerRepository customerRepository;
    public CustomerService( CustomerDocumentRepository customerDocumentRepository) {
        this.customerRepository = customerDocumentRepository;
    }


//    public List<CustomerResponse> getAllCustomers() {
//        log.info("Getting all customers...");
//        return StreamSupport.stream(
//                Spliterators.spliteratorUnknownSize(customerRepository.findAll().iterator(), Spliterator.ORDERED),
//                false).map(CustomerResponse::valueOf).collect(Collectors.toList());
//    }

    public void createCustomer(CustomerRequest customerRequest) {
        log.info("Creating customer...");
        customerRepository.save(CustomerEntity.builder()
                        .phoneNumber(customerRequest.getPhoneNumber())
                        .fullName(customerRequest.getFullName())
                        .address(customerRequest.getAddress())
                        .createdAt(customerRequest.getCreatedAt())
                        .build())
                .subscribe(savedCustomer -> {
                    log.info("Customer saved successfully: {}", savedCustomer);
                }, error -> {
                    log.error("Error occurred while saving customer: {}", error.getMessage());
                });
        log.info("Create customer method completed...");
    }
//
//    public CustomerResponse getCustomer(String id) {
//        log.info("Getting customer by id {}...", id);
//        Optional<CustomerEntity> byId = customerRepository.findById(id);
//        return byId
//                .map(CustomerResponse::valueOf)
//                .orElse(null);
//    }
}

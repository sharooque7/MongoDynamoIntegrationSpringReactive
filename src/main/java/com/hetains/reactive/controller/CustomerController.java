package com.hetains.reactive.controller;


import com.hetains.reactive.dto.CustomerRequest;
import com.hetains.reactive.dto.CustomerResponse;
import com.hetains.reactive.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<CustomerResponse> getAllCustomers() {
//        return customerService.getAllCustomers();
//    }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public CustomerResponse getCustomer(@PathVariable String id) {
//        return customerService.getCustomer(id);
//    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void getCreateCustomer(@RequestBody CustomerRequest customerRequest) {
        customerService.createCustomer(customerRequest);
    }

    @GetMapping("test")
    public String test() {
        return "tet";
    }

}
package com.hetains.reactive.dto;

import java.time.LocalDate;

import com.hetains.reactive.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private String id;

    private String phoneNumber;

    private String fullName;

    private String address;

    private LocalDate createdAt;

    public static CustomerResponse valueOf(CustomerEntity customer) {
        return builder()
                .id(customer.getId())
                .phoneNumber(customer.getPhoneNumber())
                .fullName(customer.getFullName())
                .address(customer.getAddress())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}
package com.hetains.reactive.dto;

import com.hetains.reactive.entities.Address;
import com.hetains.reactive.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithAddress {
    public User user;
    public Address address;

}

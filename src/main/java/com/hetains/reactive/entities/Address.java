package com.hetains.reactive.entities;

import com.hetains.reactive.utils.SortKeyGenerator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "address")
@DynamoDbBean
public class Address {
    @Id
    private String id;
    @Field
    private String country;
    @Field
    private  String city;

    private String sortKey;

    @DynamoDbSortKey()
    public String getSortKey() {
        return SortKeyGenerator.generateSortKey();
    }

    @DynamoDbPartitionKey
    public String getCountry() {
        return country;
    }
}

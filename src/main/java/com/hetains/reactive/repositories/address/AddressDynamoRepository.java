package com.hetains.reactive.repositories.address;

import com.hetains.reactive.entities.Address;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;

@Repository("addressDynamoRepository")
@Profile("dynamo")
public class AddressDynamoRepository implements AddressRepository{
    private  final DynamoDbTable<Address> addressDynamoDbTable;
    private  final DynamoDbAsyncTable<Address> addressDynamoDbTableAsync;

    public  AddressDynamoRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient, DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient) {
        this.addressDynamoDbTable = dynamoDbEnhancedClient.table("Address", TableSchema.fromBean(Address.class));
        this.addressDynamoDbTableAsync = dynamoDbEnhancedAsyncClient.table("Address", TableSchema.fromBean(Address.class));
    }

    public Mono<Address> save(Address address) {
       return Mono.fromFuture(this.addressDynamoDbTableAsync.putItem(address)).thenReturn(address);
    }

    @Override
    public Mono<Address> findById(String id) {
        Key key = Key.builder().
                partitionValue("India").
                sortValue("2024-02-24T17:40:36.886880Z_eb2aaff5-cda8-4906-87f9-7aae9ee9cfaf").
                build();

        GetItemEnhancedRequest getItemEnhancedRequest = GetItemEnhancedRequest.builder()
                .key(key)
                .build();
        return  Mono.fromFuture(this.addressDynamoDbTableAsync.getItem(getItemEnhancedRequest));
    }

    @Override
    public Flux<Address> findAll() {
        return null;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return null;
    }

}

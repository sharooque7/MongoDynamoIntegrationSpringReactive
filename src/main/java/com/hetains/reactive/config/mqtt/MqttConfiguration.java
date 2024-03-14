package com.hetains.reactive.config.mqtt;

import org.eclipse.paho.mqttv5.client.IMqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfiguration {

    public final  String broker = "tcp://localhost:1883";
    public  final String clientId = "RandomSubcriber";

    private  final  int subQos = 1;
    MemoryPersistence memoryPersistence = new MemoryPersistence();
    @Bean(destroyMethod = "disconnect")
    public IMqttAsyncClient mqttAsyncClient() throws MqttException {
        IMqttAsyncClient mqttAsyncClient = new MqttAsyncClient(broker,clientId,memoryPersistence) ;
        mqttAsyncClient.connect();
        System.out.println("Connect to Mqtt");
        return mqttAsyncClient;
    }
}

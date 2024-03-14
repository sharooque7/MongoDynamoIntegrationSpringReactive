package com.hetains.reactive;

import com.hetains.reactive.services.mqtt.Subscriber;
import org.eclipse.paho.mqttv5.client.IMqttAsyncClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveApplication implements CommandLineRunner {

	private final IMqttAsyncClient mqttAsyncClient;
	private  final Subscriber subscriber;
	public  ReactiveApplication(IMqttAsyncClient mqttAsyncClient, Subscriber subscriber) {
		this.mqttAsyncClient = mqttAsyncClient;
		this.subscriber = subscriber;

	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		this.subscriber.consume("temp",1);
	}
}

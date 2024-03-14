package com.hetains.reactive.services.mqtt;

import org.eclipse.paho.mqttv5.client.IMqttAsyncClient;
import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.MqttSubscription;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.stereotype.Service;

@Service
public class Subscriber {

    private  final IMqttAsyncClient mqttAsyncClient;

    public  Subscriber(IMqttAsyncClient mqttAsyncClient) {
        this.mqttAsyncClient = mqttAsyncClient;
    }

    public void  consume(String topic,int qos) {
        try{
           this.mqttAsyncClient.subscribe(new MqttSubscription[]{new MqttSubscription(topic, 0)});
            mqttAsyncClient.setCallback(new MqttCallback() {
                @Override
                public void disconnected(MqttDisconnectResponse mqttDisconnectResponse) {
                    System.out.println("Disconnected");
                }

                @Override
                public void mqttErrorOccurred(MqttException e) {

                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println(mqttMessage);
                }

                @Override
                public void deliveryComplete(IMqttToken iMqttToken) {

                }

                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                    // Not used in this example
                }

                @Override
                public void authPacketArrived(int i, MqttProperties mqttProperties) {

                }
            });
        }
        catch(MqttException e){
            e.printStackTrace();
        }
    }

    private void messageArrived(String topic, MqttMessage mqttMessage) {
        System.out.println("Received message on topic: " + topic);
        System.out.println("Message: " + new String(mqttMessage.getPayload()));
    }
}

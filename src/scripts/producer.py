import paho.mqtt.client as mqtt
import time
import random

# MQTT broker details
broker_address = "localhost"
broker_port = 1883

# Topic to publish messages to
topic = "temp"

# Create a MQTT client
client = mqtt.Client(mqtt.CallbackAPIVersion.VERSION2)


# Connect to the MQTT broker
client.connect(broker_address, broker_port)

try:
    while True:
        # Generate a random number as payload
        random_number = random.randint(0, 100)
        payload = str(random_number)

        # Publish the random number to the topic
        client.publish(topic, payload)

        print("Published message:", payload)

        # Wait for some time before publishing the next message
        time.sleep(1)  # Adjust the delay as needed

except KeyboardInterrupt:
    # Disconnect from the MQTT broker when interrupted
    client.disconnect()

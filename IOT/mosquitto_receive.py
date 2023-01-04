import paho.mqtt.client as mqtt #import the client1

broker_address="172.20.10.2" 

print("creating new instance")

client = mqtt.Client("P1") #create new instance
print("connecting to broker")

client.connect(broker_address, 1883) #connect to broker

print("Subscribing to topic","Emergency")

client.subscribe("Emergency")

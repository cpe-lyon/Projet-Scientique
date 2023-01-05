import paho.mqtt.client as mqtt
import random #Pour générer des valeurs aléatoires
import time #Pour attendre entre chaque envoi
 


MQTT_SERVER = "localhost"
MQTT_PORT = "1883"
MQTT_TOPIC = "capteur"

client = mqtt.Client() #Création client MQTT

try :
    client.connect(MQTT_SERVER,MQTT_PORT)
except :
    print("Connection to MQTT server failed")
    exit()

try:
    for i in range(0, 6):
        for j in range(0, 10):
            x = random.randint(0, 10)
            point="capteur,tag=value x="+ j +",longitude="+i+",intensity="+x
            client.publish(MQTT_TOPIC,point)
            time.sleep(0.1)
except:
    print("Publishing Error")
    exit()
client.loop_start()
client.disconnect()
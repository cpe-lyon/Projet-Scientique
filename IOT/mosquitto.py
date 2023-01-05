from operator import truediv
import RPi.GPIO as GPIO
import time
import paho.mqtt.client as paho
import datetime

# MAIL #################################################################

import smtplib, ssl

smtp_server = "smtp.office365.com"
port = 587  # For starttls
sender_email = "no-reply@atelierdesquatrecollines.com"
password = "4Collines"

#########################################################################

#### LOG ################################################################

import logging
logging.basicConfig(filename='/var/log/Alarme/Alarme.log', level=logging.DEBUG)


#########################################################################


temps1 = datetime.datetime.now()

#Variables
sound = 17 #Capteur Son
i = 0
#Connexion Broker
broker_address="127.0.0.1"

def on_publish(client,userdata,result):
    print("Donnée Publiee \n")
    pass

def callback(sound):
    global varint,i,temps1

    temps2 = datetime.datetime.now()
    deltatime = temps2-temps1


    print(temps1)
    print(temps2)

    print(deltatime, datetime.timedelta(seconds=2) )

    logging.info(temps1)
    logging.info(temps2)

    if (deltatime < datetime.timedelta(seconds=4) ) :
        temps1 = temps2
        i += 1

    else  :
        temps1 = temps2
        i = 0




    print (i)
    if (i> 10) :
        print("Son détecté", i )
        client = paho.Client("Alarme")
        client.on_publish = on_publish
        client.connect(broker_address)
        ret = client.publish("Alarme/Incendie","Alarme")
        logging.info("Alarme")
        i = 0

        # Create a secure SSL context
        context = ssl.create_default_context()

        # Try to log in to server and send email
        try:
            server = smtplib.SMTP(smtp_server,port)
            server.ehlo() # Can be omitted
            server.starttls(context=context) # Secure the connection
            server.ehlo() # Can be omittedexit()
            server.login(sender_email, password)
            receiver_email = "informatique@atelierdesquatrecollines.com"
            message = """
            Alarme Batiment 4 declenchee"""

            server.sendmail(sender_email, receiver_email, message)
        except Exception as e:
            # Print any error messages to stdout
            print(e)
        finally:
            server.quit()

        time.sleep(1200)


GPIO.setmode(GPIO.BCM)
GPIO.setup(sound,GPIO.IN)


GPIO.add_event_detect(sound,GPIO.RISING, bouncetime=300)
GPIO.add_event_callback(sound, callback) #Event déclechement Callback



while True: #Infinite Loop
    time.sleep(1)

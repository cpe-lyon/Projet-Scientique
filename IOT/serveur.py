# Program to control passerelle between Android application
# and micro-controller through USB tty
import time
import argparse
import signal
import sys
import socket
import socketserver
import serial
import threading
import paho.mqtt.client as paho




HOST           = "172.20.10.6"
UDP_PORT       = 10000
MICRO_COMMANDS = ["TH" , "HT"] # TH est pour "Temperature Humidite" et HT pour "Humidite Temperature"
FILENAME        = "values.txt"
LAST_VALUE      = ""

class ThreadedUDPRequestHandler(socketserver.BaseRequestHandler):

    def handle(self):


        data = self.request[0].strip()
        socket = self.request[1]
        current_thread = threading.current_thread()
        print("{}: client: {}, wrote: {}".format(current_thread.name, self.client_address, data))
 
        if data != "":
               
                if data.decode() in MICRO_COMMANDS: # Send message through UART
                        sendUARTMessage(data)

                elif data.decode() == "getValues()": # Sent last value received from micro-controller
                        
            
                       socket.sendto(LAST_VALUE, self.client_address) 

                    #elif data == "getValues()": # Sent last value received from micro-controller
                        # TODO: Create last_values_received as global variable      
              
                else:
                        print("Unknown message: ",data)

class ThreadedUDPServer(socketserver.ThreadingMixIn, socketserver.UDPServer):
    pass


# send serial message 
SERIALPORT = "COM3"
BAUDRATE = 115200
ser = serial.Serial()



def initUART():        
        # ser = serial.Serial(SERIALPORT, BAUDRATE)
        ser.port=SERIALPORT
        ser.baudrate=BAUDRATE
        ser.bytesize = serial.EIGHTBITS #number of bits per bytes
        ser.parity = serial.PARITY_NONE #set parity check: no parity
        ser.stopbits = serial.STOPBITS_ONE #number of stop bits
        ser.timeout = None          #block read

        # ser.timeout = 0             #non-block read
        # ser.timeout = 2              #timeout block read
        ser.xonxoff = False     #disable software flow control
        ser.rtscts = False     #disable hardware (RTS/CTS) flow control
        ser.dsrdtr = False       #disable hardware (DSR/DTR) flow control
        #ser.writeTimeout = 0     #timeout for write
        print('Starting Up Serial Monitor')
        try:
                ser.open()
        except serial.SerialException:
                print("Serial {} port not available".format(SERIALPORT))
                exit()



def sendUARTMessage(msg):
    ser.write(msg)
    print("Message <" + msg.decode("utf-8") + "> sent to micro-controller." )


#Connexion Broker
broker_address="127.0.0.1"

def on_publish(client,userdata,result):
    print("Donnée Publiee \n")
    pass


# Main program logic follows:
if __name__ == '__main__':
        initUART()
        f= open(FILENAME,"w+")
        print ('Press Ctrl-C to quit.')

        server = ThreadedUDPServer((HOST, UDP_PORT), ThreadedUDPRequestHandler)

        server_thread = threading.Thread(target=server.serve_forever)
        server_thread.daemon = True

        try:
                server_thread.start()
                print("Server started at {} port {}".format(HOST, UDP_PORT))
                while ser.isOpen() : 
                        #time.sleep(100)
                        if (ser.inWaiting() > 0): # if incoming bytes are waiting 
                                time.sleep(0.1)
                                data_str = ser.read_until(b')') # read the bytes and convert from binary array to ASCII )
                                x = data_str.decode("utf-8")
                                client = paho.Client("Alarme")
                                client.on_publish = on_publish
                                client.connect(broker_address)
                                client.publish("Alarme/Incendie","Alarme")

                                f.write(x) 
                                LAST_VALUE = data_str
                                print(data_str)
                               

        except (KeyboardInterrupt, SystemExit):
                server.shutdown()
                server.server_close()
                f.close()
                ser.close()
                exit()
import psycopg2
import serial
import time
import paho.mqtt.client as paho




# send serial message 
SERIALPORT = "COM3"
BAUDRATE = 115200
broker_address = "192.168.1.38"
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

def on_publish(client,userdata,result):
    print("Donnée Publiee \n")
    pass

if __name__ == '__main__':
        initUART()
        print ('Press Ctrl-C to quit.')
        try:
                while ser.isOpen() : 

                        #time.sleep(100)

                        if (ser.inWaiting() > 0): # if incoming bytes are waiting 
                                time.sleep(0.1)
                                data_str = ser.read_until(b')') # read the bytes and convert from binary array to ASCII )
                                x = data_str.decode("utf-8")

                                #Mosquitto
                                client = paho.Client("Alarme")
                                client.on_publish = on_publish
                                client.connect(broker_address)
                                client.publish("feu",x)

                                LAST_VALUE = data_str
                                print(data_str)
                               

        except (KeyboardInterrupt, SystemExit):
                ser.close()
                exit()
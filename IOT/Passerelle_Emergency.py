import psycopg2
import serial
import time
import paho.mqtt.client as paho
import ast
import pycurl




# send serial message 
SERIALPORT = "/dev/ttyACM0"
BAUDRATE = 115200
broker_address = "172.20.10.7"
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

def connect(data):
        """ Connect to the PostgreSQL database server """
        conn = None

     

    


        try:


                # connect to the PostgreSQL server
                print('Connecting to the PostgreSQL database...')
                conn = psycopg2.connect(
                        host="127.0.0.1",
                        database="emergency",
                        user="pgtp",
                        password="tp"
                )
                        
                # create a cursor
                cur = conn.cursor()

                list_data = ast.literal_eval(data)

                if (list_data[0] < 10 and list_data[1] < 6):
                        sql_update_query = """UPDATE Lieux SET intensite = %s WHERE adresse_y = %s AND adresse_x = %s"""
                        cur.execute(sql_update_query, (list_data[2], list_data[0], list_data[1]))
                        conn.commit()

                # close the communication with the PostgreSQL
                cur.close()
        except (Exception, psycopg2.DatabaseError) as error:
                print(error)
        finally:
                if conn is not None:
                        conn.close()
                        print('Database connection closed.')


if __name__ == '__main__':
        initUART()
        print ('Press Ctrl-C to quit.')
        try:
                while ser.isOpen() : 

                        #time.sleep(100)

                        if (ser.inWaiting() > 0): # if incoming bytes are waiting 
                                #time.sleep(0.1)
                                data_str = ser.read_until(b']') # read the bytes and convert from binary array to ASCII )
                                data_decode = data_str.decode("utf-8")

                                #Mosquitto
                                client = paho.Client("Alarme")
                                client.on_publish = on_publish
                                client.connect(broker_address)
                                
                                try : 
                                        list_data_mqtt = ast.literal_eval(data_decode)
                                        point = f'capteur,tag={str(list_data_mqtt[1])+str(list_data_mqtt[0])} x={str(list_data_mqtt[1])},y={str(list_data_mqtt[0])},i={str(list_data_mqtt[2])}'
                                        client.publish("feu",point)
                                except: 
                                        print("Erreur de conversion")

                                #PostgreSQL
                                try :
                                        list_data = ast.literal_eval(data_decode)

                                        if (list_data[0] < 10 and list_data[1] < 6):
                                                c = pycurl.Curl()
                                                c.setopt(c.URL, 'http://localhost:8000/api/lieux/'+str(list_data[0])+'/'+str(list_data[1])+'/'+str(list_data[2]))
                                                c.perform()
                                                c.close()
                                except:
                                        print("Erreur de conversion")
                                        


                                #connect(data_decode)

                                LAST_VALUE = data_str
                                print(data_str)
                               

        except (KeyboardInterrupt, SystemExit):
                ser.close()
                exit()
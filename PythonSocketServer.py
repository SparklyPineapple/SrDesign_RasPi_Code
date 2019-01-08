'''
Created on Dec 19, 2018

Sending+Receiving works with python socket on laptop


@author: Kirby Darst
'''
import socket

#SELF DEFINED FUNCTIONS START///////////////////////////////////////////////////////////////////
def acceptNewClient(socket):
    #accept client connections
    print("listening & waiting for client to connect \n")
    socket.listen()
    connection, appSocket = socket.accept()  
    print("client connected \n")
    return connection
    
def readMessage(connection):
    #read data
    print("ready to receive message \n")
    #piInput = "insert socket input here"
    try:
        byteInput = connection.recv(100)
        piInput = byteInput.decode()
        if piInput != "":
            print("this is the message read: " + piInput + "\n")
    except socket.error:
        print("no data to receive")
        return ""                
    return piInput

def sendMessage(piOutput, socket, connection):
    #send data
    newString = piOutput + '\n'
    byteString = newString.encode()
    connection.send(byteString)
    print("message sent to client \n")

def closePiSocket(socket):
    #close socket
    #wont close until client closes
    socket.close()
    print("socket closed for the day")
#SELF DEFINED FUNCTIONS END/////////////////////////////////////////////////////////////////////
#SOCKET PROGRAM START///////////////////////////////////////////////////////////////////////////
   

# create the socket (TCP) and bind it to the correct port number
piSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
piSocket.bind(('', 8080))
print("socket created \n")

#accept new client
connection = acceptNewClient(piSocket)

randomString = "this is from the python socket..... Helloooo Darling~"

    
#send data to socket
#sendMessage(randomString, piSocket, connection)

#for reading from socket
#readMessage(connection)


#SOCKET PROGRAM END/////////////////////////////////////////////////////////////////////////////







#taken from online
import socket               

s = socket.socket()        
host = '163.11.236.50'
port = 12345              
s.connect((host, port))
print(s.recv(1024))
s.close()
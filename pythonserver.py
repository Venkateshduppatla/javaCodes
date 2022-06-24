import socket
import jpysocket
from threading import Thread

# def receiveMessages(client):
# 	while 1:
# 		clientMessage = client.recv(1024).decode()
# 		chatHistory.append(clientMessage)
# 		sendMessages(clientMessage)
# def sendMessages(clientMessage):
# 	for address in clientsAddressList:
# 		address.send(str.encode(clientMessage))
serverIpAddress = "localhost"
port = 25955
server = socket.socket()
print("Waiting for Connection...")
server.bind((serverIpAddress, port))
server.listen(1)
client, clientIPAddress = server.accept()
while 1:
	sendMessage = jpysocket.jpyencode("")
	client.send(sendMessage)
	fromClient = client.recv(1024)
	fromClient = jpysocket.jpydecode(fromClient)
	print("Message From Client: " + fromClient)
	toClient = jpysocket.jpyencode(input("Enter the message: "))
	client.send(toClient)

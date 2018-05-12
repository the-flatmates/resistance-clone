import socket, sys

HOST, PORT = "35.196.166.4", 9998

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    print("Trying to connect to {}:{}".format(HOST, PORT))
    sock.connect((HOST, PORT))
    sock.sendall("Hello")

    print("Sent: Hello")

    print("Recieved: {0}".format(sock.recv(1024)))

finally:
    sock.close()

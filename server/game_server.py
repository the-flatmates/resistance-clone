#!/usr/bin/python3

import socket
import socketserver
import threading


class ResistanceCloneServer(socketserver.BaseRequestHandler):
    def __init__(self, host, port):
        self.host = host
        self.port = port
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.sock.bind((self.host, self.port))

        self.clients = []

    def getClientData(self, client, address):
            # todo add a client class
        clientName = client.recv(32).strip()
        roomCode = client.recv(6).strip()
        print("{0}'s name is {1} and their code is {2}".format(
            client, clientName, roomCode))

        self.clients.append("add client class")

    def acceptClients(self):
        self.sock.listen(10)
        while True:
            client, address = self.sock.accept()
            client.settimeout(60)
            threading.Thread(target=self.getClientData,
                             args=(client, address)).start()

    def acceptHost(self):
        self.sock.listen(1)
        client, address = self.sock.accept()

        # todo add host class? maybe just a normal client
        targeting = client.recv(1)
        idiotProof = client.recv(1)
        blindSpies = client.recv(1)
        colorBlind = client.recv(1)
        spyRevealPrompt = client.recv(1)
        numPlayers = client.recv(1)

        self.getClientData(client, address)

        self.settings = {"targeting": targeting,
                         "idiotProof": idiotProof,
                         "blindSpies": blindSpies,
                         "colorBlind": colorBlind,
                         "spyRevealPrompt": spyRevealPrompt,
                         "numPlayers": numPlayers}

    def createGame(self):
        self.acceptHost()
        self.acceptClients()


if __name__ == "__main__":
    HOST, PORT = "10.142.0.2", 9998

    print("Listening on {0}:{1}".format(HOST, PORT))

    server = socketserver.TCPServer((HOST, PORT), TcpHandler)
    server.serve_forever()

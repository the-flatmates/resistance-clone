#!/usr/bin/python3

import socket, socketserver

class ResistanceCloneServer(socketserver.BaseRequestHandler):
	def handle(self):
		self.data = self.request.recv(1024).strip()
		print("{0} wrote: {1}".format(self.client_address[0], self.data))
		self.request.sendall(self.data.upper())

	def CreateGame():
		AcceptHost()
		AcceptClients()

	def AcceptClients():
		pass

	def AcceptHost():
		pass
		self.settings = dict{targeting=targeting, 
							idiotProof=idiotProof,
							blindSpies=blindSpies,
							colorBlind=colorBlind,
							spyRevealPrompt=spyRevealPrompt,
							numPlayers=numPlayers}

if __name__ == "__main__":
	HOST, PORT = "10.142.0.2", 9998

	print("Listening on {0}:{1}".format(HOST, PORT))

	server = socketserver.TCPServer((HOST, PORT), TcpHandler)
	server.serve_forever()

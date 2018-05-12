#!/usr/bin/python3

import socket
import socketserver

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.connect(("8.8.8.8", 80))
print(s.getsockname()[0])
s.close()


class TcpHandler(socketserver.BaseRequestHandler):
    def handle(self):
        self.data = self.request.recv(1024).strip()
        print("{0} wrote: {1}".format(self.client_address[0], self.data))

        output = self.request.makefile(mode='w')
        newData = self.data.upper().decode("utf-8")
        output.write(newData)
        output.flush()

        import time
        time.sleep(2)
        print("Sent: {}".format(newData))


if __name__ == "__main__":
    HOST, PORT = "", 9998

    print("Listening on {0}:{1}".format(HOST, PORT))

    server = socketserver.TCPServer((HOST, PORT), TcpHandler)
    server.serve_forever()

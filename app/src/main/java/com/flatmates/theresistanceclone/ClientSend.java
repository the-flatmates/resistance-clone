package com.flatmates.theresistanceclone;

import android.os.AsyncTask;

import java.io.PrintWriter;
import java.net.Socket;

class ClientSend extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... args) {
        try {
            Socket socket = SocketHandler.getSocket();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            for (String arg : args) {
                out.print(arg);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.flatmates.theresistanceclone;

import android.os.AsyncTask;

import java.net.Socket;

class SocketHandler extends AsyncTask<Void, Void, Void> {
    private static Socket socket;
    private final String hostname;
    private final int port;

    SocketHandler(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    protected Void doInBackground(Void... args) {
        try {
            SocketHandler.socket = new Socket(hostname, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized Socket getSocket() {
        return socket;
    }

    public static synchronized void setSocket(Socket socket) {
        SocketHandler.socket = socket;
    }
}
package com.flatmates.theresistanceclone;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends AsyncTask<String, String, String> {
    private Socket socket;
    private String response = "";
    private String hostname;
    private int port;

    Client(String addr, int port) {
        this.hostname = addr;
        this.port = port;
    }

    @Override
    protected String doInBackground(String... args) {
        try {
            socket = new Socket(hostname, port);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            for (int i = 0; args.length > i; i++) {
                out.print(args[i]);
                out.flush();
            }
            bytesRead = inputStream.read(buffer, 0, 6);
            byteArrayOutputStream.write(buffer, 0, 6);
            response += byteArrayOutputStream.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(response);
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
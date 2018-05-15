package com.flatmates.theresistanceclone;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;

class ClientReceive extends AsyncTask<Integer, String, String> {
    private String response = "";

    @Override
    protected String doInBackground(Integer... args) {
        try {
            Socket socket = SocketHandler.getSocket();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            bytesRead = inputStream.read(buffer, 0, args[0]);
            byteArrayOutputStream.write(buffer, 0, bytesRead);
            response += byteArrayOutputStream.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}

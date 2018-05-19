package com.flatmates.theresistanceclone;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

class ClientReceive extends AsyncTask<Void, String, String> {
    private String response = "";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    protected String doInBackground(Void... args) {
        try {
            Socket socket = SocketHandler.getSocket();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            int messageLength;
            inputStream.read(buffer, 0, 3);
            messageLength = Integer.parseInt(new String(Arrays.copyOfRange(buffer, 0, 2)).trim());
            bytesRead = inputStream.read(buffer, 0, messageLength);
            byteArrayOutputStream.write(buffer, 0, bytesRead);
            response += byteArrayOutputStream.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}

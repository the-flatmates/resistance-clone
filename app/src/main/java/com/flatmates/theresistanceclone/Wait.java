package com.flatmates.theresistanceclone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Wait extends AppCompatActivity {
    private String hostname;
    private int port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String room_code = intent.getStringExtra(HostGame.ROOM_CODE);
        hostname = intent.getStringExtra(MainActivity.HOSTNAME);
        port = intent.getIntExtra(MainActivity.PORT, 0);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.tv_room_code);
        textView.setText(room_code);
    }

    /**
     * Called when the user taps the ready button
     */
    public void ready(View view) {
        Client myClient = new Client(hostname, port);
        String[] params = {"1"};
        String response = "";
        try {
            response = myClient.execute(params).get();
        } catch (Exception e) {
            System.out.println(e);
        }
        Context context = getApplicationContext();
        String message = "Ready to start!";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}

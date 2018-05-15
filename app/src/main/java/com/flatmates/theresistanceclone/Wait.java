package com.flatmates.theresistanceclone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Wait extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String room_code = intent.getStringExtra("com.flatmates.theresistanceclone.ROOM_CODE");

        // Capture the layout's TextView and set the string as its text
        TextView tv_room_code = findViewById(R.id.tv_room_code);

        if (!room_code.isEmpty()) {
            tv_room_code.setText(room_code);
        } else {
            ClientReceive r = new ClientReceive();
            Integer[] bytes = {6};
            try {
                String message = r.execute(bytes).get();
                tv_room_code.setText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Called when the user taps the ready button
     */
    public void ready(View view) {
        String[] params = {"1"};
        ClientSend c = new ClientSend();
        try {
            c.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Context context = getApplicationContext();
        String message = "Ready to start!";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}

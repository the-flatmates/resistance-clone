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

        // Capture the layout's TextView and set the string as its text
        TextView tv_room_code = findViewById(R.id.tv_room_code);

        tv_room_code.setText(Game.getRoomCode());
    }

    /**
     * Called when the user taps the ready button
     */
    public void ready(View view) {
        String[] params = {"r"};
        ClientSend c = new ClientSend();
        try {
            c.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Context context = getApplicationContext();
        String message = "Ready to start!";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Wait.this, SpyReveal.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.flatmates.theresistanceclone.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the user taps the Send button
     */
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, HostWait.class);
//        EditText editText = findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

    /**
     * Called when the user taps the host game button
     */
    public void hostGame(View view) {
        Intent intent = new Intent(this, HostGame.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps the join game button
     */
    public void joinGame(View view) {
        Intent intent = new Intent(this, JoinGame.class);
        startActivity(intent);
    }
}

package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    public static final String HOSTNAME = "com.flatmates.theresistanceclone.HOSTNAME";
    public static final String PORT = "com.flatmates.theresistanceclone.PORT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the user taps the host game button
     */
    public void hostGame(View view) {
        Intent intent = new Intent(this, HostGame.class);
        intent.putExtra(HOSTNAME, "35.196.166.4");
        intent.putExtra(PORT, 9998);
        startActivity(intent);
    }

    /**
     * Called when the user taps the join game button
     */
    public void joinGame(View view) {
        Intent intent = new Intent(this, JoinGame.class);
        intent.putExtra(HOSTNAME, "35.196.166.4");
        intent.putExtra(PORT, 9998);
        startActivity(intent);
    }
}

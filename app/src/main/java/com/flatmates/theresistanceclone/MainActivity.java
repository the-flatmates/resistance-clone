package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private SocketHandler socketHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the user taps the host game button
     */
    public void hostGame(View view) {
        socketHandler = new SocketHandler("35.196.166.4", 9998);
        socketHandler.execute();
        Game.setRole("host");
        Intent intent = new Intent(this, HostGame.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps the join game button
     */
    public void joinGame(View view) {
        socketHandler = new SocketHandler("35.196.166.4", 9998);
        socketHandler.execute();
        Game.setRole("player");
        Intent intent = new Intent(this, JoinGame.class);
        startActivity(intent);
    }
}

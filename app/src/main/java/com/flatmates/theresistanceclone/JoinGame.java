package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class JoinGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);
    }

    /**
     * Called when the user taps the submit button
     */
    public void submit(View view) {
        Intent intent = new Intent(this, PlayerWait.class);
        startActivity(intent);
    }
}

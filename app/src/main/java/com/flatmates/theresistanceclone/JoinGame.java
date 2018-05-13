package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class JoinGame extends AppCompatActivity {
    private Client myClient;
    private EditText te_room_code;
    private EditText te_player_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String hostname = intent.getStringExtra(MainActivity.HOSTNAME);
        int port = intent.getIntExtra(MainActivity.PORT, 0);

        myClient = new Client(hostname, port);

        te_room_code = findViewById(R.id.te_room_code);
        te_player_name = findViewById(R.id.te_player_name);
    }

    /**
     * Called when the user taps the submit button
     */
    public void submit(View view) {
        Intent intent = new Intent(this, PlayerWait.class);
        String[] params = {"c",
                te_room_code.getText().toString().toUpperCase(),
                te_player_name.getText().toString()};
//        String response = "";
        try {
            myClient.execute(params);
        } catch (Exception e) {
            System.out.println(e);
        }
        startActivity(intent);
    }
}

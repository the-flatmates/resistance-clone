package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class HostGame extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.flatmates.theresistanceclone.MESSAGE";

    private TextView tv_num_players;
    private static final int SERVERPORT = 9998;
    private static final String SERVER_IP = "35.196.166.4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);
        SeekBar sb_num_players = findViewById(R.id.sb_num_players);
        tv_num_players = findViewById(R.id.tv_num_players);

        sb_num_players.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int num_players = 5;

            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                num_players = value + 5;
                tv_num_players.setText(String.valueOf(num_players));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    /**
     * Called when the user taps the submit button
     */
    public void submit(View view) throws IOException {
        Intent intent = new Intent(this, HostWait.class);
        Client myClient = new Client(SERVER_IP, SERVERPORT);
        String[] params = {"h",
                "ttFFt",
                ((TextView) findViewById(R.id.tv_num_players)).getText().toString(),
                ((EditText) findViewById(R.id.te_host_player_name)).getText().toString()};
        String response = "";
        try {
            response = myClient.execute(params).get();
        } catch (Exception e) {
            System.out.println(e);
        }
        intent.putExtra(EXTRA_MESSAGE, response);
        startActivity(intent);
    }
}

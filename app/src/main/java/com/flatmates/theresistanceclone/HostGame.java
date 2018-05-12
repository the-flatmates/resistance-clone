package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class HostGame extends AppCompatActivity {
    private TextView tv_num_players;

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
    public void submit(View view) {
        Intent intent = new Intent(this, HostWait.class);
        startActivity(intent);
    }
}

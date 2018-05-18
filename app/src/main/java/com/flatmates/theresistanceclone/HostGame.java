package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class HostGame extends AppCompatActivity {
    private TextView tv_num_players;
    private EditText te_host_player_name;
    private final Switch[] switches = new Switch[5];
    private int num_players = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);
        SeekBar sb_num_players = findViewById(R.id.sb_num_players);
        tv_num_players = findViewById(R.id.tv_num_players);
        te_host_player_name = findViewById(R.id.te_host_player_name);
        switches[0] = findViewById(R.id.sw_settings_targeting);
        switches[1] = findViewById(R.id.sw_settings_idiot_proof);
        switches[2] = findViewById(R.id.sw_settings_blind_spies);
        switches[3] = findViewById(R.id.sw_settings_color_blind);
        switches[4] = findViewById(R.id.sw_settings_spy_reveal);

        sb_num_players.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

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
        Intent intent = new Intent(this, Wait.class);
        sendSettings();
        receiveResponse();
        startActivity(intent);
    }

    private String getSettings() {
        StringBuilder settings = new StringBuilder();
        for (Switch sw : switches) {
            if (sw.isChecked()) {
                settings.append("t");
            } else {
                settings.append("f");
            }
        }
        return settings.toString();
    }

    private void sendSettings() {
        String settings = getSettings();
        String settingsMessage = Game.createMessage("h", settings + String.format(Locale.US, "%02d", num_players));
        String playerName = Game.createMessage("n", te_host_player_name.getText().toString());
        String[] params = {"h", settingsMessage, playerName};
        ClientSend c = new ClientSend();
        try {
            c.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void receiveResponse() {
        ClientReceive r = new ClientReceive();
        try {
            r.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Game.setRoomCode(Game.getResponse());
    }
}

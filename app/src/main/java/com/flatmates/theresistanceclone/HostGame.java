package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.util.Locale;

public class HostGame extends AppCompatActivity {
    public static final String HOSTNAME = "com.flatmates.theresistanceclone.HOSTNAME";
    public static final String PORT = "com.flatmates.theresistanceclone.PORT";
    public static final String ROOM_CODE = "com.flatmates.theresistanceclone.ROOM_CODE";
    private TextView tv_num_players;
    private EditText te_host_player_name;
    private Switch[] switches = new Switch[5];
    private int num_players = 5;
    private String hostname;
    private int port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        hostname = intent.getStringExtra(MainActivity.HOSTNAME);
        port = intent.getIntExtra(MainActivity.PORT, 0);

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
    public void submit(View view) throws IOException {
        Client myClient = new Client(hostname, port);
        Intent intent = new Intent(this, Wait.class);

        String settings = "";

        for (int i = 0; i < switches.length; i++) {
            if (switches[i].isChecked()) {
                settings += "t";
            } else {
                settings += "f";
            }
        }

        String[] params = {"h",
                settings,
                String.format(Locale.US, "%02d", num_players),
                te_host_player_name.getText().toString()};
        String response = "";
        try {
            response = myClient.execute(params).get();
        } catch (Exception e) {
            System.out.println(e);
        }
        intent.putExtra(ROOM_CODE, response);
        intent.putExtra(HOSTNAME, hostname);
        intent.putExtra(PORT, port);
        startActivity(intent);
    }
}

package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

public class Wait extends AppCompatActivity {
    private Button btn_ready_wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        TextView tv_room_code = findViewById(R.id.tv_room_code);
        tv_room_code.setText(Game.getRoomCode());
        btn_ready_wait = findViewById(R.id.btn_ready_wait);
    }

    /**
     * Called when the user taps the ready button
     */
    public void ready(View view) {
        sendReady();
        String settings = receiveSettings();

        setGameSettings(settings);

        if (Game.getRole().equals("host")) {
            Intent intent = new Intent(Wait.this, SpyReveal.class);
            startActivity(intent);
        } else {
            btn_ready_wait.setEnabled(false);
            String start = receiveStart();
            if (start.equals("s")) {
                // TODO: Determine if leader or not, then start mission screen
//                Intent intent = new Intent(Wait.this, Mission.class);
//                startActivity(intent);
            }
        }
    }

    private void sendReady() {
        ClientSend c = new ClientSend();
        String[] params = {"r000"};
        try {
            c.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String receiveSettings() {
        ClientReceive r = new ClientReceive();
        String response = "";
        try {
            response = r.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private void setGameSettings(String settings) {
        try {
            Log.d("Wait", settings);
            JSONObject settingsJSON = new JSONObject(settings);
            Game.setAllegiance(settingsJSON.getString("allegiance"));
            Game.setNumPlayers(settingsJSON.getInt("numPlayers"));
            Game.setLeaderOrder(settingsJSON.getJSONArray("leaderOrder").toString().replace("},{", " ,").split(" "));
            Game.setTargeting(settingsJSON.getJSONObject("settings").getBoolean("targeting"));
            Game.setIdiotProof(settingsJSON.getJSONObject("settings").getBoolean("idiotProof"));
            Game.setBlindSpies(settingsJSON.getJSONObject("settings").getBoolean("blindSpies"));
            Game.setSpyReveal(settingsJSON.getJSONObject("settings").getBoolean("spyRevealPrompt"));
            Game.setColorBlind(settingsJSON.getJSONObject("settings").getBoolean("colorBlind"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String receiveStart() {
        ClientReceive r = new ClientReceive();
        String response = "";
        try {
            response = r.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}

package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        String settings = Game.receiveMessage();

        setGameSettings(settings);

        if (Game.getRole().equals("host")) {
            if (Game.isSpyReveal()) {
                Intent intent = new Intent(Wait.this, SpyReveal.class);
                startActivity(intent);
            } else {
                sendStart();
                getNextActivity("s");
            }
        } else {
            getNextActivity(Game.receiveMessage());
        }
    }

    private void getNextActivity(String result) {
        btn_ready_wait.setEnabled(false);
        if (result.equals("s")) {
            if (Game.getPlayerName().equals(Game.getLeaderOrder()[Game.getLeader()])) {
                Intent intent = new Intent(Wait.this, SelectMissionTeam.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(Wait.this, VoteMissionTeam.class);
                Game.setMission(Integer.valueOf(Game.receiveMessage()));
                Game.setTeamSelection(Game.receiveMessage());
                startActivity(intent);
            }
        }
    }

    private void sendStart() {
        String[] params = {"s000"};
        Game.sendMessage(params);
    }

    private void sendReady() {
        String[] params = {"r000"};
        Game.sendMessage(params);
    }

    public int[] JSONArrayToIntArray(JSONArray jsonArray) {
        int[] intArray = new int[jsonArray.length()];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = jsonArray.optInt(i);
        }
        return intArray;
    }

    public List<int[]> JSONArrayToList(JSONArray jsonArray) {
        List<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < jsonArray.length(); i++) {
            int[] temp = JSONArrayToIntArray(jsonArray.optJSONArray(i));
            list.add(temp);
        }
        return list;
    }

    private String[] formatLeaderOrder(String strLeaderOrder) {
        String substring = strLeaderOrder.substring(1, strLeaderOrder.length() - 1);
        return substring.replaceAll("\"", "").split(",");
    }

    private void setGameSettings(String settings) {
        try {
            JSONObject settingsJSON = new JSONObject(settings);
            Game.setAllegiance(settingsJSON.getString("allegiance"));
            Game.setNumPlayers(settingsJSON.getInt("numPlayers"));
            Game.setLeaderOrder(formatLeaderOrder(settingsJSON.getJSONArray("leaderOrder").toString()));
            Game.setMissionInfo(JSONArrayToList(settingsJSON.getJSONArray("roundInfo")));
            Game.setTargeting(settingsJSON.getJSONObject("settings").getBoolean("targeting"));
            Game.setIdiotProof(settingsJSON.getJSONObject("settings").getBoolean("idiotProof"));
            Game.setBlindSpies(settingsJSON.getJSONObject("settings").getBoolean("blindSpies"));
            Game.setSpyReveal(settingsJSON.getJSONObject("settings").getBoolean("spyRevealPrompt"));
            Game.setColorBlind(settingsJSON.getJSONObject("settings").getBoolean("colorBlind"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

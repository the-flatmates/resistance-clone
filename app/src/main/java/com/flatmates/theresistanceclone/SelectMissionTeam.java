package com.flatmates.theresistanceclone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class SelectMissionTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mission_team);

        updateMissionMarkers();
        setVoteTracker();
        setMissionInfo();
        listPlayerNames();
    }

    private void updateMissionMarkers() {
        LinearLayout ll_mission_markers = findViewById(R.id.ll_mission_markers);
        for (int i = 0; i < ll_mission_markers.getChildCount(); i++) {
            TextView tv_mission_marker = (TextView) ll_mission_markers.getChildAt(i);
            if (Game.getMissionResults()[i] == -1) {
                tv_mission_marker.setClickable(false);
                tv_mission_marker.setTextColor(Color.RED);
            } else if (Game.getMissionResults()[i] == 1) {
                tv_mission_marker.setClickable(false);
                tv_mission_marker.setTextColor(Color.BLUE);
            } else {
                if (Game.isTargeting()) {
                    tv_mission_marker.setClickable(true);
                }
            }
            if ((Game.getMission() - 1) == i) {
                tv_mission_marker.setBackgroundResource(R.drawable.ic_radio_button_checked_black_24dp);
            }
        }
    }

    private void setVoteTracker() {
        LinearLayout ll_vote_tracker = findViewById(R.id.ll_vote_tracker);
        TextView tv_vote_track = (TextView) ll_vote_tracker.getChildAt(Game.getVoteTrack() - 1);
        tv_vote_track.setBackgroundResource(R.drawable.ic_my_location_black_24dp);
    }

    private void setMissionInfo() {
        TextView tv_mission_title = findViewById(R.id.tv_mission_title);
        String missionText = "Mission " + String.valueOf(Game.getMission()) + ":";
        tv_mission_title.setText(missionText);
        TextView tv_mission_info = findViewById(R.id.tv_mission_info);
        String missionSize = Integer.toString(Game.getMissionInfo().get(Game.getMission() - 1)[0]);
        String numFails = Integer.toString(Game.getMissionInfo().get(Game.getMission() - 1)[1]);
        String missionInfo = missionSize + " (" + numFails + " Fail)";
        tv_mission_info.setText(missionInfo);
    }

    private void listPlayerNames() {
        LinearLayout ll_team_selection = findViewById(R.id.ll_team_selection);
        for (String playerName : Game.getLeaderOrder()) {
            ToggleButton toggleButton = new ToggleButton(this);
            toggleButton.setText(playerName);
            toggleButton.setTextOff(playerName);
            toggleButton.setTextOn(playerName);
            ll_team_selection.addView(toggleButton);
        }
    }

    public void onClickMission(View view) {
        if (Game.isTargeting()) {
            TextView tv_mission_marker = findViewById(view.getId());
            int mission = Integer.valueOf(String.valueOf(tv_mission_marker.getTag()));
            if (Game.getMissionResults()[mission - 1] == 0) {
                LinearLayout ll_mission_markers = findViewById(R.id.ll_mission_markers);
                TextView tv_mission_marker_old = (TextView) ll_mission_markers.getChildAt(Game.getMission() - 1);
                tv_mission_marker_old.setBackgroundResource(R.drawable.ic_panorama_fish_eye_black_24dp);
                Game.setMission(mission);
                tv_mission_marker.setBackgroundResource(R.drawable.ic_radio_button_checked_black_24dp);
                setMissionInfo();
            }
        }
    }

    private boolean isTeamValid() {
        LinearLayout ll_team_selection = findViewById(R.id.ll_team_selection);
        ArrayList<String> teamSelection = new ArrayList<>();
        int teamCount = 0;
        for (int i = 0; i < ll_team_selection.getChildCount(); i++) {
            ToggleButton toggleButton = (ToggleButton) ll_team_selection.getChildAt(i);
            if (toggleButton.isChecked()) {
                teamCount++;
                teamSelection.add(toggleButton.getText().toString());
            }
        }
        if (teamCount == Game.getMissionInfo().get(Game.getMission() - 1)[0]) {
            Game.setCurrentTeam(teamSelection.toArray(new String[0]));
            return true;
        }
        return false;
    }

    private void sendTeamSelection() {
        String missionMessage = "";
        if (Game.isTargeting()) {
            missionMessage = Game.createMessage("m", String.valueOf(Game.getMission()));
        }
        StringBuilder teamSelectionMessage = new StringBuilder();
        for (String playerName : Game.getCurrentTeam()) {
            teamSelectionMessage.append(Game.createMessage("t", playerName));
        }
        String[] params = {missionMessage, teamSelectionMessage.toString()};
        ClientSend c = new ClientSend();
        try {
            c.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmTeamSelection(View view) {
        if (isTeamValid()) {
            Intent intent = new Intent(SelectMissionTeam.this, VoteMissionTeam.class);
            sendTeamSelection();
            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            String message = "Invalid team selection!";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}

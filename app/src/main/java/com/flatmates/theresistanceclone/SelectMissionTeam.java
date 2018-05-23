package com.flatmates.theresistanceclone;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SelectMissionTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mission_team);

        setVoteTrack();
        setMissionInfo();
        listPlayerNames();
    }

    private void setVoteTrack() {
        LinearLayout ll_vote_tracker = findViewById(R.id.ll_vote_tracker);
        TextView tv_vote_track = (TextView) ll_vote_tracker.getChildAt(Game.getVoteTrack() - 1);
        tv_vote_track.setTextColor(Color.RED);
    }

    private void setMissionInfo() {
        TextView tv_mission_info = findViewById(R.id.tv_mission_info);
        String missionSize = Integer.toString(Game.getMissionInfo().get(Game.getMission())[0]);
        String numFails = Integer.toString(Game.getMissionInfo().get(Game.getMission())[1]);
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
}

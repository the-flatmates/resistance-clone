package com.flatmates.theresistanceclone;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;


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
            if (Game.getTargeting()) {
                tv_mission_marker.setClickable(true);
            }
            if (Game.getMissionResults()[i] == -1) {
                tv_mission_marker.setTextColor(Color.RED);
            } else if (Game.getMissionResults()[i] == 1) {
                tv_mission_marker.setTextColor(Color.BLUE);
            } else {
                tv_mission_marker.setTextColor(Color.BLACK);
            }
            if ((Game.getMission() - 1) == i) {
                tv_mission_marker.setTextColor(Color.GREEN);
            }
        }
    }

    private void setVoteTracker() {
        LinearLayout ll_vote_tracker = findViewById(R.id.ll_vote_tracker);
        TextView tv_vote_track = (TextView) ll_vote_tracker.getChildAt(Game.getVoteTrack() - 1);
        tv_vote_track.setTextColor(Color.RED);
    }

    private void setMissionInfo() {
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
        Log.d("Targeting", Boolean.toString(Game.getTargeting()));
        if (Game.getTargeting()) {
            TextView tv_mission_marker = findViewById(view.getId());
            Log.d("Tag", String.valueOf(tv_mission_marker.getTag()));
            int mission = Integer.valueOf(String.valueOf(tv_mission_marker.getTag()));
            Log.d("Mission", String.valueOf(mission));
            if (Game.getMissionResults()[mission - 1] == 0) {
                LinearLayout ll_mission_markers = findViewById(R.id.ll_mission_markers);
                TextView tv_mission_marker_old = (TextView) ll_mission_markers.getChildAt(Game.getMission() - 1);
                tv_mission_marker_old.setTextColor(Color.BLACK);
                Game.setMission(mission);
                tv_mission_marker.setTextColor(Color.GREEN);
                setMissionInfo();
            }
        }
    }

    public void confirmMission(View view) {

    }
}

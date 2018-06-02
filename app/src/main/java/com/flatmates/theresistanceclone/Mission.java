package com.flatmates.theresistanceclone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Mission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        updateMissionMarkers();
        setVoteTracker();
        setMissionInfo();
        showTeamSelection();
    }

    private void updateMissionMarkers() {
        LinearLayout ll_mission_markers = findViewById(R.id.ll_mission_markers);
        for (int i = 0; i < ll_mission_markers.getChildCount(); i++) {
            TextView tv_mission_marker = (TextView) ll_mission_markers.getChildAt(i);
            if (Game.getMissionResults()[i] == -1) {
                tv_mission_marker.setTextColor(Color.RED);
            } else if (Game.getMissionResults()[i] == 1) {
                tv_mission_marker.setTextColor(Color.BLUE);
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

    private void showTeamSelection() {
        LinearLayout ll_team_selection = findViewById(R.id.ll_team_selection);
        for (String playerName : Game.getCurrentTeam()) {
            TextView tv_player_name = new TextView(this);
            tv_player_name.setText(playerName);
            tv_player_name.setGravity(Gravity.CENTER_HORIZONTAL);
            tv_player_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            ll_team_selection.addView(tv_player_name);
        }
    }

    public void onClickSuccess(View view) {
        Context context = getApplicationContext();
        String message = "Mission succeeded!";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        sendVote("t");
        getNextActivity();
    }

    public void onClickFail(View view) {
        Context context = getApplicationContext();
        String message = "Mission failed!";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        sendVote("f");
        getNextActivity();
    }

    private void getNextActivity() {
        Game.setMissionResults(Game.getMission() - 1, Integer.valueOf(Game.receiveMessage()));
        // TODO: Check for game win condition
        Game.incrementLeader();
        if (Game.getPlayerName().equals(Game.getLeaderOrder()[Game.getLeader()])) {
            Intent intent = new Intent(Mission.this, SelectMissionTeam.class);
            Game.incrementMission();
            startActivity(intent);
        } else {
            Intent intent = new Intent(Mission.this, VoteMissionTeam.class);
            Game.setMission(Integer.valueOf(Game.receiveMessage()));
            Game.setTeamSelection(Game.receiveMessage());
            startActivity(intent);
        }
    }

    private void sendVote(String vote) {
        String voteMessage = Game.createMessage("c", vote);
        String[] params = {voteMessage};
        Game.sendMessage(params);
    }
}

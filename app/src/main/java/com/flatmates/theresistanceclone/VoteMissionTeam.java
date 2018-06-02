package com.flatmates.theresistanceclone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class VoteMissionTeam extends AppCompatActivity {
    private Button btn_accept;
    private Button btn_reject;
    private TextView tv_mission_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_mission_team);
        btn_accept = findViewById(R.id.btn_accept);
        btn_reject = findViewById(R.id.btn_reject);
        tv_mission_progress = findViewById(R.id.tv_mission_progress);
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

    public void onClickAccept(View view) {
        Context context = getApplicationContext();
        disableButtons();
        String message = "Mission accepted!";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        sendVote("t");
        getNextActivity(Game.receiveMessage());
    }

    public void onClickReject(View view) {
        Context context = getApplicationContext();
        disableButtons();
        String message = "Mission rejected!";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        sendVote("f");
        getNextActivity(Game.receiveMessage());
    }

    private void getNextActivity(String result) {
        if (result.equals("T")) {
            Game.setVoteTrack(1);
            boolean isOnMission = false;
            for (String playerName : Game.getCurrentTeam()) {
                if (Game.getPlayerName().equals(playerName)) {
                    isOnMission = true;
                    Intent intent = new Intent(VoteMissionTeam.this, Mission.class);
                    startActivity(intent);
                    break;
                }
            }
            if (!isOnMission) {
                tv_mission_progress.setVisibility(View.VISIBLE);
                Game.setMissionResults(Game.getMission() - 1, Integer.valueOf(Game.receiveMessage()));
                Game.incrementMission();
                // TODO: Check for game win condition
                Game.incrementLeader();
                if (Game.getPlayerName().equals(Game.getLeaderOrder()[Game.getLeader()])) {
                    Intent intent = new Intent(VoteMissionTeam.this, SelectMissionTeam.class);
                    startActivity(intent);
                } else {
                    Game.setMission(Integer.valueOf(Game.receiveMessage()));
                    Game.setTeamSelection(Game.receiveMessage());
                    finish();
                    startActivity(getIntent());
                }
            }
        } else if (result.equals("F")) {
            if (Game.getVoteTrack() < 5) {
                Game.setVoteTrack(Game.getVoteTrack() + 1);
            } else {
                // TODO: Check for game win condition
                Game.setMissionResults(Game.getMission(), -1);
                Game.setVoteTrack(1);
            }
            Game.incrementLeader();
            if (Game.getPlayerName().equals(Game.getLeaderOrder()[Game.getLeader()])) {
                Intent intent = new Intent(VoteMissionTeam.this, SelectMissionTeam.class);
                startActivity(intent);
            } else {
                Game.setMission(Integer.valueOf(Game.receiveMessage()));
                Game.setTeamSelection(Game.receiveMessage());
                finish();
                startActivity(getIntent());
            }
        }
    }

    private void sendVote(String vote) {
        String voteMessage = Game.createMessage("v", vote);
        String[] params = {voteMessage};
        Game.sendMessage(params);
    }

    private void disableButtons() {
        btn_accept.setEnabled(false);
        btn_reject.setEnabled(false);
    }
}

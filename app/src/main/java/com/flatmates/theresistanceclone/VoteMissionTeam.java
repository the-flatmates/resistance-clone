package com.flatmates.theresistanceclone;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

public class VoteMissionTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_mission_team);
        getMissionSelection();
        setTeamSelection(getTeamSelection());
        updateMissionMarkers();
        setVoteTracker();
        setMissionInfo();
        showTeamSelection();
    }

    private void getMissionSelection() {
        ClientReceive r = new ClientReceive();
        String response = "";
        try {
            response = r.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Game.setMission(Integer.valueOf(response));
    }

    private void setTeamSelection(String jsonTeamSelection) {
        try {
            String teamSelection = new JSONArray(jsonTeamSelection).toString();
            String substring = teamSelection.substring(1, teamSelection.length() - 1);
            Game.setCurrentTeam(substring.replaceAll("\"", "").split(","));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTeamSelection() {
        ClientReceive r = new ClientReceive();
        String response = "";
        try {
            response = r.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
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
        String message = "Mission accepted!";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        sendVote("t");
    }

    public void onClickReject(View view) {
        Context context = getApplicationContext();
        String message = "Mission rejected!";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        sendVote("f");
    }

    private void sendVote(String vote) {
        ClientSend c = new ClientSend();
        String voteMessage = Game.createMessage("v", vote);
        String[] params = {voteMessage};
        try {
            c.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

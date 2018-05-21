package com.flatmates.theresistanceclone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinGame extends AppCompatActivity {
    private EditText te_room_code;
    private EditText te_player_name;
    private Button btn_join_game_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);
        te_room_code = findViewById(R.id.te_room_code);
        te_player_name = findViewById(R.id.te_player_name);
        btn_join_game_submit = findViewById(R.id.btn_join_game_submit);

        te_room_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (te_room_code.getText().length() == 6 && te_player_name.getText().length() > 0) {
                    btn_join_game_submit.setEnabled(true);
                } else {
                    btn_join_game_submit.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        te_player_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (te_room_code.getText().length() == 6 && te_player_name.getText().length() > 0) {
                    btn_join_game_submit.setEnabled(true);
                } else {
                    btn_join_game_submit.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    /**
     * Called when the user taps the submit button
     */
    public void submit(View view) {
        Game.setPlayerName(te_player_name.getText().toString());
        Game.setRoomCode(te_room_code.getText().toString().toUpperCase());
        sendSettings();
        String response = receiveResponse();
        if (response.equals("0")) {
            Context context = getApplicationContext();
            String message = "Room does not exist!";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, Wait.class);
            startActivity(intent);
        }
    }

    private void sendSettings() {
        ClientSend c = new ClientSend();
        String roomCodeMessage = Game.createMessage("p", Game.getRoomCode());
        String playerNameMessage = Game.createMessage("n", Game.getPlayerName());
        String[] params = {"p", roomCodeMessage, playerNameMessage};
        try {
            c.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String receiveResponse() {
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

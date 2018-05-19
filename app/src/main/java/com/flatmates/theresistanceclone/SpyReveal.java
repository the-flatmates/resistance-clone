package com.flatmates.theresistanceclone;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class SpyReveal extends AppCompatActivity {
    TextToSpeech tts;
    TextView prompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spy_reveal);

        prompt = findViewById(R.id.tv_reveal_prompt);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });
    }

    public void readPrompt(View view) {
        String toSpeak = prompt.getText().toString();
        tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void sendStart(View view) {
        ClientSend c = new ClientSend();
        String[] params = {"s000"};
        try {
            c.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: Determine if leader or not, then start mission screen
//        Intent intent = new Intent(Wait.this, Mission.class);
//        startActivity(intent);
    }
}

package com.example.u6485142.retrogame2018s1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 *  Created by Wei Wang
 *  Modified by Yuehan Zhao
 */
/**
 * This is the fianl Activity when player's ship dead
 * Two button, button_1 for restart the game, button_2 by exit the game
 * one text view for showing the final score
 * the layout of this activity is activity_end
 * inspired by https://developer.android.com/ and https://stackoverflow.com/
 */
public class EndActivity extends AppCompatActivity {
    private Button button_1;
    private Button button_2;
    private SoundPool soundPool;
    private int gameID;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent intentScore=getIntent();
        String score=intentScore.getStringExtra("score");
        textView=findViewById(R.id.txtOne);
        textView.setTextColor(Color.YELLOW);
        textView.setTextSize(50);
        textView.setText(score);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        gameID = soundPool.load(this, R.raw.game, 1);

        button_1 =findViewById(R.id.but_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(gameID, 1, 1, 0, -1, 1.0f);

                /* move from this activity to new activity*/
                Intent intent=new Intent(EndActivity.this, MainActivity.class);
                startActivity(intent);
                EndActivity.this.finish();
            }
        });
        button_2 = findViewById(R.id.but_3);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

}

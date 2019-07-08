package com.example.u6485142.retrogame2018s1;

        import android.content.Intent;
        import android.media.AudioManager;
        import android.media.SoundPool;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;

/**
 *  Created by Wei Wang
 *  Modified by Yuehan Zhao
 */
/**
 * This is the first Activity when player enter the game
 * Two button, button_1 for enter the game, button_2 by exit the game
 * the layout of this activity is activity_start
 * inspired by https://developer.android.com/ and https://stackoverflow.com/
 */

public class StartActivity extends AppCompatActivity {
    private Button button_1;
    private Button button_2;
    private SoundPool soundPool;
    private int gameID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        /*Play bgm*/
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        gameID = soundPool.load(this, R.raw.game, 1);

        button_1=findViewById(R.id.but_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(gameID, 1, 1, 0, -1, 1.0f);
                /* move from this activity to new activity*/
                Intent intent=new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                StartActivity.this.finish();
            }
        });
        button_2 = findViewById(R.id.but_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
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
 * This is the main Activity when player choose to start game
 * just showing the item in GameView
 * one method toGameEndActivit is used to record the score and switch to the EndActivity
 * the layout of this activity is activity_main
 * inspired by https://developer.android.com/ and https://stackoverflow.com/
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
    //this method toGameEndActivit is used to record the score and switch to the EndActivity
    public void toGameEndActivity(int x){
        /* record socre*/
        System.out.println("yes"+x);
        Intent intent=new Intent();
        intent.putExtra("score",String.valueOf(x));
        /* move from this activity to new activity*/
        intent.setClass(MainActivity.this,EndActivity.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();
    }
}

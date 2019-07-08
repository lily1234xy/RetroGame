package com.example.u6485142.retrogame2018s1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;


/**
 * Created by Yuehan Zhao
 *
 * This class is used to build awards for player if alive.
 * There are two kinds of award :Health award and Power up Award.
 * health_award make player's life +1. Power up Award improve player's bullet, get more damage, speed and has two lines.
 */
public class Award {
    public static enum AwardType{
        health_award,powerup_award
    }
        /*
        speed refers to the speed of the award.
        AwardX is the current x coordinate of award.
        AwardY is the current y coordinate of award.
        AwardWidth is the width of the bitmap award, all a static number (50) in AwardTest.
        AwardHeight is the height of the bitmap award, all a static number (50) in AwardTest.
         */
        public double speed;
        public double AwardX;
        public double AwardY;
        public double AwardWidth;
        public double AwardHeight;
        public AwardType awardType;
        public Bitmap award;
        Random random=new Random();

        public Award( double AwardX, double AwardY, AwardType awardType) {
            this.awardType=awardType;
            switch (awardType) {
                case health_award:
                    this.AwardX = AwardX;
                    this.AwardY = AwardY;
                    this.speed = 10;
                    break;
                case powerup_award:
                    this.AwardX = AwardX;
                    this.AwardY = AwardY;
                    this.speed = 5;
                    break;
            }
        }
        /*draw awards on canvas.  */
        public void drawself(Canvas canvas, Paint paint){
            canvas.drawBitmap(award, (int)AwardX, (int)AwardY, paint);
        }

    /** This is used in /test/ AwardTest.
     * @param x the x coordinate of award
     */
        public void setX(double x){
            AwardX=x;
        }

    /**This is used in /test/ AwardTest.
     * @param y the y coordinate of award
     */
        public void setY(double y){
            AwardY=y;
        }

    /**
     * @return X coordinate of award
     */
        public double getX(){
            return AwardX;
        }

    /**
     * @return y coordinate of award
     */
        public double getY(){
            return AwardY;
        }
        /*
        different type of award has different moving logic.
         */
        public void move(){
            switch (awardType){
                case powerup_award:

                    this.AwardY+=speed;
                    break;
                case health_award:

                    this.AwardY+=speed;
                    break;
            }


        }

    public void setBitmap(Bitmap bitmap){
        this.award=bitmap;
    }

    /** When get the health award, if the player's life is not full, increase player's life by 1.
     * @param player refers to Player plane.
     */
    public void makeLifeUp(Player player){
            if(player.getLife()<player.getMaxLife())
                player.setLife(player.getLife()+1);
    }

    /**
     * @return the width of award bitmap, or 50 in AwardTest.
     */
    public double getWidth(){
            if(award!=null)
                AwardWidth=award.getWidth();
            else AwardWidth=50;
            return AwardWidth;
        }

    /**
     * @return the height of award bitmap, or 50 in AwardTest.
     */
        public double getHeight(){
            if(award!=null)
                AwardHeight=award.getHeight();
            else AwardHeight=50;
            return AwardHeight;

        }


    }



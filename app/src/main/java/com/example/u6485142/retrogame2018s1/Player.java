package com.example.u6485142.retrogame2018s1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 *  Created by Min Liu
 */

public class Player {

    /* some basic attributes of the player */
    public double PlayerX;
    public double PlayerY ;
    public double PlayerWidth;
    public double PlayerHeight;

    public int playScore = 0;
    public int life;
    public Bitmap player;

    public final int MAX_LIFE = 5;

    public Player( double PlayerX, double PlayerY, int life) {

        this.PlayerX = PlayerX;
        this.PlayerY = PlayerY;
        this.life = life;

    }

    /* draw the image of the player */
    public void drawself(Canvas canvas, Paint paint){
        canvas.drawBitmap(player, (int)PlayerX, (int)PlayerY, paint);
    }

    /* set the x coordinate of the player */
    public void setX(double x){
        PlayerX = x;
    }

    /* set the y coordinate of the player */
    public void setY(double y){
        PlayerY = y;
    }

    /* get the x coordinate of the player */
    public double getX(){
        return PlayerX;
    }

    /* get the y coordinate of the player */
    public double getY(){
        return PlayerY;
    }

    /* get the width of the player */
    public double getWidth(){
        if(player != null)
            PlayerWidth = player.getWidth();
        else PlayerWidth = 20;
          return PlayerWidth;

    }

    /* get the height of the player */
    public double getHeight(){
        if(player != null)
            PlayerHeight = player.getHeight();
        else PlayerHeight = 20;

        return PlayerHeight;
    }

    /**
     * determine whether the player collides the award.
     *
     */

    public boolean getAward(Award award){
        double awardX = award.getX();
        double awardY = award.getY();
        double award_width = award.getWidth();
        double award_height = award.getHeight();

        boolean collide=false;
        if (PlayerX <= awardX+award_width-40 && PlayerX >= awardX-20) {
            if(PlayerY <= awardY+award_height && PlayerY >= awardY-50)
                collide = true;

        }
        return collide;
    }

    /**
     * determine whether the player collides the enemy.
     *
     */
    public boolean Collide_enemy(Enemy enemy){
        double EnemyX = enemy.getX();
        double EnemyY = enemy.getY();
        double enemyHeight = enemy.getHeight();
        double enemyWidth = enemy.getWidth();
        double distanceX = Math.abs(PlayerX - EnemyX);
        double distanceY = Math.abs(PlayerY - EnemyY);

        if (distanceX <= (enemyWidth + player.getWidth())/2 && distanceY <= (enemyHeight + player.getHeight())/2){
            return true;
        }else {
            return false;
        }
    }
    public void setBitmap(Bitmap bitmap){this.player = bitmap;}

    /**
     * reduce the life of the player based on the bullet type, and return the remaining life.
     * @param bullet the bullet that hit the player.
     * @return the remaining life of the player.
     */
    public int lifeRemain(Bullet bullet) {
        life -= bullet.damage;
        return life;
    }

    public int getMaxLife() {
        return MAX_LIFE;
    }

    /* get the score of the player */
    public int getScore(){return playScore;}
    /* add the score of the player */
    public void setScore(int x){playScore += x;}
    /* get the life of the player */
    public int getLife(){return life;}
    /* set the life of the player */
    public void setLife(int x){life=x;}

}

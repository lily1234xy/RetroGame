package com.example.u6485142.retrogame2018s1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Tingyu Pan
 *
 * This class is used to build bullet for enemyplanes.
 */

public class Enemy {
    //Enum Enemy plane types of five for boss and different version
    public static enum EnemyType{
        small,middle,large,boss,bossoldier
    }

    public double EnemyX;//Enemy Location
    public double EnemyY;
    public double EnemyWidth;//Enemy Bitmap size to calculate distance.etc.
    public double EnemyHeight;
    public boolean destroyed = false;//Enemy death flag


    public EnemyType enemytype;
    public Bitmap enemy;
    public int health;//The health blood of each enemyplane
    public int enemyscore;//EnemyScore
    /*
    * Constructor for each Enemy types
    * Assign diffrent health and scores to each type
    * Boss has the most health and score along with the soldies.
    * */
    public Enemy( double EnemyX, double EnemyY,EnemyType enemytype) {

        this.EnemyX =EnemyX;
        this.EnemyY=EnemyY;
        this.enemytype=enemytype;
        switch (enemytype){
            case small:
                health = 1;
                enemyscore = 1;
                break;
            case middle:
                health = 2;
                enemyscore = 5;
                break;
            case large:
                health = 3;
                enemyscore = 10;
                break;
            case boss:
                health = 20;
                enemyscore = 1000;
                break;
            case bossoldier:
                health = 1;
                enemyscore = 50;
        }

    }
    //draw enemy on canvas
    public void drawself(Canvas canvas, Paint paint){
        canvas.drawBitmap(enemy, (int) EnemyX, (int)EnemyY, paint);
    }
    //draw explosion cloud after enemy destroyed
    public void explosion(Bitmap explo,Canvas canvas, Paint paint){
        canvas.drawBitmap(explo, (int)EnemyX, (int)EnemyY,paint);
    }
    //set and get the enemy location
    public void setX(double x){
        EnemyX =x;
    }
    public void setY(double y){
        EnemyY=y;
    }
    public double getX(){
        return EnemyX;
    }
    public double getY(){
        return EnemyY;
    }
    /*
    * Assign different move track method to each plane
    * Based on the distance from player, enemies can automitically fly to the player for
    * collision and more targeting bullet shooting place
    * */
    public void move(Player player){
        double PlayerX = player.getX();
        double PlayerY = player.getY();
        double distanceX = PlayerX - EnemyX;
        double distanceY = PlayerY - EnemyY;
        switch (enemytype){
            case small:
                EnemyY += 10;//small enemies just moving straightforward slowly
                break;
            case middle://middle ones can chase the player from every directions including backtrack
                if (distanceX >= 0 && distanceY >= 0){
                    EnemyY += 10;
                    EnemyX += 15;}
                else if (distanceX < 0 && distanceY >= 0){
                    EnemyY += 10;
                    EnemyX -= 15;
                }else if (distanceX >= 0 && distanceY < 0){
                    EnemyY -= 10;
                    EnemyX += 15;
                }else if (distanceX < 0 && distanceY < 0){
                    EnemyY -= 10;
                    EnemyX -= 15;
                }
                break;
            case large://large ones could only chase from parrallell ways and moving forward
                if (distanceX <= 0){
                    EnemyY += 5;
                    EnemyX -= 10;}
                else {
                    EnemyY += 5;
                    EnemyX += 10;
                }
                break;
            case boss://Boss Slowly moving and stop still halfway
                if (EnemyY <= GameView.GameHeight/3)
                {EnemyY += 1;
                    break;}
                else{
                    break;
                }
            case bossoldier://boss send smallplanes with very fast speed to chase player
                if (distanceX >= 0 && distanceY >= 0){
                    EnemyY += 15;
                    EnemyX += 15;}
                else if (distanceX < 0 && distanceY >= 0){
                    EnemyY += 15;
                    EnemyX -= 15;
                }else if (distanceX >= 0 && distanceY < 0){
                    EnemyY -= 15;
                    EnemyX += 15;
                }else if (distanceX < 0 && distanceY < 0){
                    EnemyY -= 15;
                    EnemyX -= 15;
                }
                break;
        }

    }
    /*Set the death states based on the health and location
    Enemies will be destroyed whether heath count down to 0 and going out of the canvas
    The collision was evaluated independently below
    * */
    public boolean checkDestroyed(){
        if (health <= 0 || EnemyY > GameView.GameHeight || EnemyX < 0 - enemy.getWidth()/2 || EnemyX > GameView.GameWidth + enemy.getWidth()/2)
            destroyed = true;
        return destroyed;
    }



    //Check the distance between player and enemies to return a boolean whether it's collsion
    public boolean Collide_player(Player player){
        double PlayerX = player.getX();
        double PlayerY = player.getY();
        double playerHeight = player.getHeight();
        double playerWidth = player.getWidth();
        double distanceX = Math.abs(PlayerX - EnemyX);
        double distanceY = Math.abs(PlayerY - EnemyY);
        //double distance = distance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
        if (distanceX <= (playerWidth + enemy.getWidth())/2 && distanceY <= (playerHeight + enemy.getHeight())/2){
            return true;
        }else {
            return false;
        }
    }
    //Enemies health count down upon the bullet collision based on the damage
    public void attacked(Bullet B){
        health -= B.getDamage();
        System.out.println("health"+health);

    }

    public void setBitmap(Bitmap bitmap){this.enemy=bitmap;}
    //set height and weight for testing
    public double getWidth(){
        if (enemy!=null)
            EnemyWidth=enemy.getWidth();
        else EnemyWidth=100;
        return EnemyWidth;
    }
    public double getHeight(){
        if(enemy!=null)
            EnemyHeight=enemy.getHeight();
        else EnemyHeight=100;

        return EnemyHeight;
    }
    public int getScore(){return enemyscore;}//Score data getter
    public int getHealth() {return health;}//Health data getter


}
package com.example.u6485142.retrogame2018s1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * Created by Yuehan Zhao
 *
 * This class is used to build bullet for enemies and player.
 */

public class Bullet {

    /*
    There are seven enum type bullet for different objects.
     */
    public static enum BulletType{
        bullet_player,bullet_enemy_small,bullet_boss_one, bullet_powerup,bullet_enemy_middle,bullet_enemy_large,bullet_boss_two
    }

    /*
    Different type of bullet has different speed and damage.
    BulletX is the current x location for bullet.
    BulletY is the current y location for bullet.
     */
    public double speed;
    public double BulletX;
    public double BulletY;
    public int damage;
    public BulletType Bullettype;
    public Bitmap bullet;

    public Bullet( double BulletX, double BulletY, BulletType Bullettype){

        this.Bullettype=Bullettype;

        switch (Bullettype){
            case bullet_player:
                this.BulletX=BulletX-10;
                this.BulletY=BulletY;
                this.speed=20;
                this.damage=1;
                break;
            case bullet_enemy_small:
                this.BulletX=BulletX;
                this.BulletY=BulletY;
                this.speed=20;
                this.damage=1;
                break;

            case bullet_powerup:
                this.BulletX=BulletX;
                this.BulletY=BulletY;
                this.speed=20;
                this.damage=2;
                break;
            case bullet_enemy_middle:
                this.BulletX=BulletX;
                this.BulletY=BulletY;
                this.speed=20;
                this.damage=2;
                break;
            case bullet_enemy_large:
                this.BulletX=BulletX;
                this.BulletY=BulletY;
                this.speed=20;
                this.damage=2;
                break;
            case bullet_boss_one:
                this.BulletX=BulletX;
                this.BulletY=BulletY;
                this.speed=15;
                this.damage=1;
                break;
            case bullet_boss_two:
                this.BulletX=BulletX;
                this.BulletY=BulletY;
                this.speed=20;
                this.damage=1;
                break;
                
        }
    }


    /*draw bullet on canvas. */
    public void drawself(Canvas canvas,Paint paint){
        canvas.drawBitmap(bullet,(int)BulletX,(int)BulletY,paint);
    }
    /*
    Different bullet has different moving logic. +speed means the bullet goes from top to bottom, which
    refers to enemy bullet. -speed means bullet goes from bottom to top, which refers to player bullet.
     */
    public void move(){
        switch (Bullettype){
            case bullet_enemy_small:
                this.BulletY=BulletY+speed;
                break;
            case bullet_player:
                this.BulletY=BulletY-speed;
                break;
            case bullet_powerup:
                this.BulletY=BulletY-speed;
                break;
            case bullet_enemy_middle:
                this.BulletY=BulletY+speed;
                break;
            case bullet_enemy_large:
                this.BulletY=BulletY+speed;
                break;
            case bullet_boss_one:
                this.BulletY=BulletY+speed;
                break;
            case bullet_boss_two:
                this.BulletY=BulletY+speed;
                break;

        }

    }


    /**This function is used to check whether the player's bullet collide with enemy's plane.
     * @param enemy  refers to enemy's plane
     * @return If the bullet collide the enemy,return true, else false.
     */
    public boolean Collide_enemy(Enemy enemy){
        double enemyX=enemy.getX();
        double enemyY=enemy.getY();
        double enemy_width=enemy.getWidth();
        double enemy_height=enemy.getHeight();
        boolean collide=false;
        if (BulletX<=enemyX+enemy_width-20 && BulletX >=enemyX-80) {
            if (BulletY <= enemyY + enemy_height -30 && BulletY >= enemyY)
                collide = true;
        }

        return collide;
    }

    /**This function is used to check whether the enemy's bullet collide with player's plane.
     * @param player player's plane.
     * @return If the bullet collide the player,return true, else false.
     */
    public boolean Collide_player(Player player){
        double playerX=player.getX();
        double playerY=player.getY();
        double player_width=player.getWidth();
        double player_height=player.getHeight();

        boolean collide=false;
        if (BulletX<=playerX+player_width-20 && BulletX >=playerX-80) {
            if(BulletY<=playerY+player_height && BulletY>=playerY)
                collide = true;
        }
        return collide;
    }

    public void setBitmap(Bitmap bitmap){this.bullet=bitmap;}


    /** This function is used to get damage of different kinds of bullet.
     * @return damage of bullet.
     */
    public int getDamage(){
        return damage;
    }

}

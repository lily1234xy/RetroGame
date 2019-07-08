package com.example.u6485142.retrogame2018s1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  The idea of using surfaceview for our game inspired by https://github.com/HurTeng/StormPlane.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder gameHolder;
    private Canvas gameCanvas;
    private Bitmap background;
    private int background_Height;
    private int background_width;
    private int location_y1;
    private int location_y2;
    private Rect origin_rect;
    private Rect move_1rect;
    private Rect move_2rect;
    private MainActivity mainActivity;


    public static double GameHeight;          //the height of the screen
    public static double GameWidth;           //the width of the screen
    private Paint paint;
    private Paint paint_score;
    private Paint paint_life;
    private double clickX;   // the x coordinate of the mouse when clicks down
    private double clickY;   // the y coordinate of the mouse when clicks down
    private double playerX;  // the x coordinate of the player when the mouse clicks down
    private double playerY;  // the x coordinate of the player when the mouse clicks down
    private double differentX;  // the distance between the player and the mouse
    private double differentY;

    /**
     * bitmaps that are used in the game
     */
    private Bitmap smallenemyplane;
    private Bitmap middleenemyplane;
    private Bitmap largeenemyplane;
    private Bitmap bossplane;
    private Bitmap bossoldier;
    private Bitmap player;
    private Bitmap playerbullet;
    private Bitmap smallenemybullet;
    private Bitmap middleenemybullet;
    private Bitmap largeenemybullet;
    private Bitmap bossbulletone;
    private Bitmap bossbullettwo;
    private Bitmap bulletaward;
    private Bitmap healthaward;
    private Bitmap powerupbullet;
    public  Player gameplayer;
    private Bitmap explosion;
    private Bitmap bossexplosion;
    private boolean gamerunning;
    private boolean getpoweraward;      // if the player get power up award

    /* build a new soundpool and use a hashmap to save all of the sounds that need to be
     * used in this game
     */
    private SoundPool soundPool;
    HashMap<Integer, Integer> soundID = new HashMap<>();


    private long frame = 0;             //The frame to build bullets for players and awards.
    private long EnemyFrame = 0;        //The frame to build new enemy's planes and bullets.
    private long PowerupFrame = 0;     //The time that a power up award lasts.

    private ArrayList<Bullet> PlayerBullet;             // player's bullet
    private ArrayList<Bullet> SmallEnemyBullet;         // small enemy plane's bullet
    private ArrayList<Bullet> MiddleEnemyBullet;        // middle enemy plane's bullet
    private ArrayList<Bullet> LargeEnemyBullet;         // large enemy plane's bullet
    private ArrayList<Bullet> BossEnemyBullet;          // boss's bullet
    private ArrayList<Enemy> SmallEnemyPlane;           // small enemy planes
    private ArrayList<Enemy> MiddleEnemyPlane;          // middle enemy planes
    private ArrayList<Enemy> LargeEnemyPlane;           // large enemy planes
    private ArrayList<Enemy> Boss;                      // boss  planes
    private ArrayList<Enemy> Bossoldiers;               // boss's soldiers
    private ArrayList<Award> PowerupAward;              // power up award
    private ArrayList<Award> HealthAward;               // health award.


    public GameView(Context context) {
        this(context, null);

        mainActivity = (MainActivity) context;

        paint = new Paint();
        paint_score= new Paint();
        paint_life=new Paint();
        paint_life.setColor(Color.WHITE);
        paint_score.setColor(Color.WHITE);
        paint.setColor(Color.WHITE);

        /**
         *  Created by Min Liu
         */
        /**
         * add all of the sounds into the soundpool and each label of the sound will
         * be called when using the sound.
         * The code of adding sound into the game view is based on the soundpool
         * code from: <https://blog.csdn.net/gophers/article/details/38513255>
         * The sound sources are from: <https://github.com/HurTeng/StormPlane/tree/master/app/src/main/res/raw>
         */
        soundPool=new SoundPool(8, 0,0);
        soundID.put(1, soundPool.load(context, R.raw.shoot, 1));
        soundID.put(2, soundPool.load(context, R.raw.explosion, 1));
        soundID.put(3, soundPool.load(context, R.raw.explosion2, 1));
        soundID.put(4, soundPool.load(context, R.raw.explosion3, 1));
        soundID.put(5, soundPool.load(context, R.raw.bigexplosion, 1));
        soundID.put(6, soundPool.load(context, R.raw.get_goods, 1));
        soundID.put(7, soundPool.load(context, R.raw.button, 1));
        soundID.put(8, soundPool.load(context, R.raw.game, 1));

    }

    public GameView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * initial the game.
     * The bitmap of all the bullets, the player plane are downloaded from https://github.com/HurTeng/StormPlane.
     * The bitmap of the enemy planes,boss and explosion are coming from https://github.com/pedrorfernandes/raiden/tree/raiden/assets
     * The bitmap of beginning, gaming and ending background are designed by Yuehan Zhao
     */
    private void init() {
        gameHolder = getHolder();
        gameHolder.addCallback(this);
        powerupbullet = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_blue);
        smallenemyplane = BitmapFactory.decodeResource(getResources(), R.drawable.smallenemyplane);
        middleenemyplane = BitmapFactory.decodeResource(getResources(), R.drawable.middleenemyplane);
        largeenemyplane = BitmapFactory.decodeResource(getResources(), R.drawable.largeenemyplane);
        bossplane = BitmapFactory.decodeResource(getResources(), R.drawable.bossplane);
        bossoldier = BitmapFactory.decodeResource(getResources(), R.drawable.smallenemyplane);
        player = BitmapFactory.decodeResource(getResources(), R.drawable.myplane1);
        playerbullet = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_red);
        smallenemybullet = BitmapFactory.decodeResource(getResources(), R.drawable.smallenemybullet);
        middleenemybullet = BitmapFactory.decodeResource(getResources(), R.drawable.middleenemybullet);
        largeenemybullet = BitmapFactory.decodeResource(getResources(), R.drawable.largenemybullet);
        bossbulletone = BitmapFactory.decodeResource(getResources(), R.drawable.bossbulletone);
        bossbullettwo = BitmapFactory.decodeResource(getResources(), R.drawable.bossbullettwo);
        explosion = BitmapFactory.decodeResource(getResources(),R.drawable.cloud);
        bossexplosion = BitmapFactory.decodeResource(getResources(), R.drawable.mushroomcloud);
        bulletaward = BitmapFactory.decodeResource(getResources(), R.drawable.award1);
        healthaward = BitmapFactory.decodeResource(getResources(), R.drawable.healthaward);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundtwo);
        background_Height = background.getHeight();
        background_width = background.getWidth();
        origin_rect = new Rect(0, 0, background_width, background_Height);
        location_y1 = 0;
        location_y2 = -background_Height;
        move_1rect = new Rect(0, location_y1, (int)GameWidth, (int)GameHeight);
        move_2rect = new Rect(0, location_y2, (int)GameWidth, 0);
        PlayerBullet = new ArrayList<>();
        SmallEnemyBullet = new ArrayList<>();
        MiddleEnemyBullet = new ArrayList<>();
        LargeEnemyBullet = new ArrayList<>();
        BossEnemyBullet = new ArrayList<>();
        SmallEnemyPlane = new ArrayList<>();
        MiddleEnemyPlane = new ArrayList<>();
        LargeEnemyPlane = new ArrayList<>();
        SmallEnemyPlane = new ArrayList<>();
        Boss=new ArrayList<>();
        Bossoldiers = new ArrayList<>();
       HealthAward=new ArrayList<>();
       PowerupAward =new ArrayList<>();
        getpoweraward=false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gamerunning = true;

        /*get the height and width of the screen*/
        GameHeight = this.getHeight();
        GameWidth = this.getWidth();
        gameplayer = new Player( GameWidth/2, GameHeight-200, 5);
        gameplayer.setBitmap(player);
        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gamerunning = false;

    }


    /** Build and move bullets are created by Yuehan Zhao
     *  Build and move enemy planes are created by Tingyu Pan
     *
     */
    private void gamelogic() {

        frame++;
        EnemyFrame++;

        /*If the player get power up, change the bullet mode from playerbullet to powerupbullet. */
        if (getpoweraward == true) {
            PowerupFrame += 2;
            if (frame % 8 == 0) {
                /*generate two lines of bullets*/
                for (int dualbullet = 0; dualbullet < 2; dualbullet++) {
                    Bullet player_bullet_one = new Bullet(gameplayer.getX() + 100 * dualbullet, gameplayer.getY() - player.getHeight() / 2, Bullet.BulletType.bullet_powerup);
                    player_bullet_one.setBitmap(powerupbullet);
                    PlayerBullet.add(player_bullet_one);
                    /*If the powerupFrame is 50, 100,150 ……, end the power up mode */
                    if (PowerupFrame % 50 == 0) {
                        getpoweraward = false;
                        PowerupFrame = 0;
                    }
                }
            }
        } else if (frame % 15 == 0) {
            /* normal bullet mode for player's plane*/
            Bullet player_bullet_one = new Bullet(gameplayer.getX() + 80, gameplayer.getY() - player.getHeight() / 2, Bullet.BulletType.bullet_player);
            player_bullet_one.setBitmap(playerbullet);
            PlayerBullet.add(player_bullet_one);
        }
        /*small enemy randomly appear at the top of canvas frequently*/
        if (EnemyFrame % 40 == 0) {
            Enemy small_enemy = new Enemy(Math.random() * GameWidth, 30, Enemy.EnemyType.small);
            small_enemy.setBitmap(smallenemyplane);
            SmallEnemyPlane.add(small_enemy);

        }
        /*middle enemy randomly appear at the top of canvas*/
        if (EnemyFrame % 90== 0) {
            Enemy middle_enemy = new Enemy(Math.random() * GameWidth, 30, Enemy.EnemyType.middle);
            middle_enemy.setBitmap(middleenemyplane);
            MiddleEnemyPlane.add(middle_enemy);

        }
        /*large enemy randomly appear at the top of canvas*/
        if (EnemyFrame % 150 == 0) {
            Enemy large_enemy = new Enemy(Math.random() * GameWidth, 30, Enemy.EnemyType.large);
            large_enemy.setBitmap(largeenemyplane);
            LargeEnemyPlane.add(large_enemy);
        }
        /*boss randomly appear at the middle around of canvas between a long time period gap and the suicide soldier planes
        * appears in a line just beneath the boss at the same time*/
        if (EnemyFrame % 600 == 0) {
            Enemy boss_enemy = new Enemy(Math.random() * (GameWidth / 3), 100, Enemy.EnemyType.boss);
            boss_enemy.setBitmap(bossplane);
            Boss.add(boss_enemy);
            for (int i = 0; i <= GameWidth; i += GameWidth / 10) {
                Enemy boss_soldier = new Enemy((double) i, 150, Enemy.EnemyType.bossoldier);
                boss_soldier.setBitmap(bossoldier);
                Bossoldiers.add(boss_soldier);
            }
        }
        /* build small enemy plane's bullets*/
        if (EnemyFrame % 60 == 0)
            for (int i = 0; i < SmallEnemyPlane.size(); i++) {
                Bullet small_enemy_bullet = new Bullet(SmallEnemyPlane.get(i).getX() + smallenemyplane.getWidth() / 2.5, SmallEnemyPlane.get(i).getY() + smallenemyplane.getHeight() / 2, Bullet.BulletType.bullet_enemy_small);
                small_enemy_bullet.setBitmap(smallenemybullet);
                SmallEnemyBullet.add(small_enemy_bullet);
            }
        /* build middle enemy plane's bullets*/
        if (EnemyFrame % 60 == 0)
            for (int i = 0; i < MiddleEnemyPlane.size(); i++) {
                Bullet middle_enemy_bullet = new Bullet(MiddleEnemyPlane.get(i).getX() + middleenemyplane.getWidth() / 6, MiddleEnemyPlane.get(i).getY() + middleenemyplane.getHeight() / 1.7, Bullet.BulletType.bullet_enemy_middle);
                middle_enemy_bullet.setBitmap(middleenemybullet);
                MiddleEnemyBullet.add(middle_enemy_bullet);

            }
        /* build large enemy plane's bullets*/
        if (EnemyFrame % 60 == 0)
            for (int i = 0; i < LargeEnemyPlane.size(); i++) {
                for (int dualbullet = 0; dualbullet < 2; dualbullet++) {
                    Bullet large_enemy_bullet = new Bullet(LargeEnemyPlane.get(i).getX() + dualbullet * 100, LargeEnemyPlane.get(i).getY() + largeenemyplane.getHeight() / 2, Bullet.BulletType.bullet_enemy_large);
                    large_enemy_bullet.setBitmap(largeenemybullet);
                    LargeEnemyBullet.add(large_enemy_bullet);
                }

            }
        /* build first kind of boss bullets, which has three lines of bullets*/
        if (EnemyFrame % 60 == 0)
            for (int i = 0; i < Boss.size(); i++) {
                for (int tripplebullet = 0; tripplebullet < 3; tripplebullet++) {
                    Bullet boss_enemy_bullet_one = new Bullet(Boss.get(i).getX() + 100 + 300 * tripplebullet, Boss.get(i).getY() + bossplane.getHeight() / 1.5, Bullet.BulletType.bullet_boss_one);
                    boss_enemy_bullet_one.setBitmap(bossbulletone);
                    BossEnemyBullet.add(boss_enemy_bullet_one);
                }
            }
        /* build second kind of boss bullets, which has five lines of bullets*/
        if (EnemyFrame % 60 == 0)
            for (int i = 0; i < Boss.size(); i++) {
                for (int quintuplebullet = 0; quintuplebullet < 5; quintuplebullet++) {
                    Bullet boss_enemy_bullet_two = new Bullet(Boss.get(i).getX() - 100 + 250 * quintuplebullet, Boss.get(i).getY() + bossplane.getHeight() / 1.5, Bullet.BulletType.bullet_boss_two);
                    boss_enemy_bullet_two.setBitmap(bossbullettwo);
                    BossEnemyBullet.add(boss_enemy_bullet_two);
                }
            }

        /* build power up award*/
        if (frame % 300== 0) {
            Award award = new Award(Math.random() * (GameWidth / 2 - 1 + 1), 0, Award.AwardType.powerup_award);
            award.setBitmap(bulletaward);
            PowerupAward.add(award);
        }
        /* build health award*/
        if (frame % 400 == 0) {
            Award award = new Award(Math.random() * (GameWidth / 2 - 1 + 1), 0, Award.AwardType.health_award);
            award.setBitmap(healthaward);
            HealthAward.add(award);
        }
        /* make the power up award move*/
        for (int i = 0; i < PowerupAward.size(); i++) {
            Award award = PowerupAward.get(i);
            award.move();
        }
        /* make the health award move*/
        for (int i = 0; i < HealthAward.size(); i++) {
            Award award = HealthAward.get(i);
            award.move();
        }

        
        /* make the player bullet move*/
        for (int i = 0; i < PlayerBullet.size(); i++) {
            Bullet bullet = PlayerBullet.get(i);
            bullet.move();
        }
        /* make the small enemy plane's bullet move*/
        for (int i = 0; i < SmallEnemyBullet.size(); i++) {
            Bullet bullet = SmallEnemyBullet.get(i);
            bullet.move();
        }
        /* make the middle enemy plane's bullet move*/
        for (int i = 0; i < MiddleEnemyBullet.size(); i++) {
            Bullet bullet = MiddleEnemyBullet.get(i);
            bullet.move();
        }
        /* make the large enemy plane's bullet move*/
        for (int i = 0; i < LargeEnemyBullet.size(); i++) {
            Bullet bullet = LargeEnemyBullet.get(i);
            bullet.move();
        }
        /* make the boss's bullet move*/
        for (int i = 0; i < BossEnemyBullet.size(); i++) {
            Bullet bullet = BossEnemyBullet.get(i);
            bullet.move();
        }
        for (int i = 0; i < SmallEnemyPlane.size(); i++) {
            Enemy enemy = SmallEnemyPlane.get(i);
            enemy.move(gameplayer);
        }
        for (int i = 0; i < MiddleEnemyPlane.size(); i++) {
            Enemy enemy = MiddleEnemyPlane.get(i);
            enemy.move(gameplayer);
        }
        for (int i = 0; i < LargeEnemyPlane.size(); i++) {
            Enemy enemy = LargeEnemyPlane.get(i);
            enemy.move(gameplayer);
        }
        for (int i = 0; i < Boss.size(); i++) {
            Enemy enemy = Boss.get(i);
            enemy.move(gameplayer);
        }
        for (int i = 0; i < Bossoldiers.size(); i++) {
            Enemy enemy = Bossoldiers.get(i);
            enemy.move(gameplayer);
        }
    }
/**
 *  Created by Min Liu, Tingyu Pan ,Yuehan Zhao and Wei Wang.
 */
    /**
     * This function is used to check if the player crashes with enemy's planes
     * if the health of enemy's plane is 0, remove the enemy plane, add score to player.
     * if crash, game over.
     * @param enemies enemy planes.
     */
    public void crash(ArrayList<Enemy> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
           Enemy enemy= enemies.get(i);

                if (gameplayer.Collide_enemy(enemy)) {
                    /* created by Wei Wang */
                    /*if player has no life, intent to ending interface*/
                    mainActivity.toGameEndActivity(gameplayer.getScore());
                }
                if (enemy.checkDestroyed() || enemy.Collide_player(gameplayer)) {
                    /* created by Tingyu Pan */
                    /*if enemy has been checked no health, colliding player or out of canvas,
                    remove the enemy and draw the explosion*/
                    enemies.remove(enemy);
                    gameplayer.setScore(enemy.getScore());
                    soundPool.play(soundID.get(2), 1, 1, 0, 0, 1.0f); //play sound
                    if (enemies==Boss)
                        enemy.explosion(bossexplosion,gameCanvas,paint);
                    else
                    enemy.explosion(explosion, gameCanvas, paint);
                } else
                    enemy.drawself(gameCanvas, paint);

        }
    }
/**
 *  Created by  Yuehan Zhao, Min Liu, Tingyu Pan
 */
    /**
    /** This function is used to check whether player's bullet hits enemy
     * if hits, remove player's bullet, and reduce enemy plane's life.
     * @param enemies enemy planes
     */
    public void player_hit_enemy(ArrayList<Enemy> enemies) {
        for (int i = 0; i < PlayerBullet.size(); i++) {
            Bullet bullet = PlayerBullet.get(i);
            bullet.drawself(gameCanvas, paint);
            for (Enemy enemy : enemies) {
                if (bullet.Collide_enemy(enemy)) {
                    PlayerBullet.remove(bullet);
                    /* reduce enemy plane's life*/
                    enemy.attacked(bullet);
                    soundPool.play(soundID.get(1), 1, 1, 0, 0, 1.0f); //play sound
                }
            }
        }
    }
/**
 *  Created by  Yuehan Zhao, Min Liu,  Wei Wang.
 */
    /** This function is used to check if the enemy's bullet hits player
     * if hits, remove this bullet and reduce player's health
     * if player has no life, game over.
     * @param bullets different enemy's bullet
     */
    public void enemy_hit_player(ArrayList<Bullet> bullets){
        for (int i=0;i<bullets.size();i++){
            Bullet bullet=bullets.get(i);
            if(bullet.Collide_player(gameplayer)){
                bullets.remove(bullet);
                /* created by Min Liu */
                /* reduce the life based on the type of bullet and determine whether the player is dead */
                gameplayer.lifeRemain(bullet);
                if (gameplayer.life <= 0) {
                    /* created by Wei Wang */
                    /*if player has no life, intent to ending interface*/
                    mainActivity.toGameEndActivity(gameplayer.getScore());
           }
            }else bullet.drawself(gameCanvas,paint);
        }
    }


    /** Created by Wei Wang, Yuehan Zhao, Tingyu Pan, Min Liu.
     * draw function
     */
    public void draw() {
        gameCanvas = gameHolder.lockCanvas();
        if (gameCanvas != null) {
            try {
                /*add enemy plane to enemies*/
                ArrayList<ArrayList<Enemy>> enemies=new ArrayList<>();
                enemies.add(SmallEnemyPlane);
                enemies.add(MiddleEnemyPlane);
                enemies.add(LargeEnemyPlane);
                enemies.add(Boss);
                enemies.add(Bossoldiers);

                /*add enemy bullets to enemybullets*/
                ArrayList<ArrayList<Bullet>> enemybullets=new ArrayList<>();
                enemybullets.add(SmallEnemyBullet);
                enemybullets.add(MiddleEnemyBullet);
                enemybullets.add(LargeEnemyBullet);
                enemybullets.add(BossEnemyBullet);
                /**
                 *  Created by Wei Wang
                 */
                /**
                * this part use android.graphics.Rect to generate new background step by step -- make it looks like "move"
                 * inspired by https://developer.android.com/ and https://stackoverflow.com/
                */
                gameCanvas.drawColor(Color.BLACK);
                // moveSpeed to set the "move speed", more bigger more faster
                int moveSpeed = background_Height / 40;
                //need two backgroud move to avoid no picture in each time step
                location_y1 += moveSpeed;
                location_y2 += moveSpeed;
                move_1rect.set(0, location_y1, background_width, background_Height + location_y1);
                move_2rect.set(0, location_y2, background_width, background_Height + location_y2);
                gameCanvas.drawBitmap(background, origin_rect, move_1rect, paint);
                gameCanvas.drawBitmap(background, origin_rect, move_2rect, paint);
                //when the picture totally move out of screen, reset the loction of them
                if (location_y1 >= GameHeight) {
                    location_y1 = -background_Height + moveSpeed;
                }
                if (location_y2 >= GameHeight) {
                    location_y2 = -background_Height + moveSpeed;
                }
                gameplayer.drawself(gameCanvas, paint);

                paint_score.setTextSize(80);
                paint_life.setTextSize(80);

                gameCanvas.drawText("score: "+String.valueOf(gameplayer.getScore()), (float)GameWidth*1/2, (float)100, paint_score);
                gameCanvas.drawText("life: "+String.valueOf(gameplayer.getLife()), (float)GameWidth*1/10, (float)100, paint_life);

                /**
                 *  Created by Tingyu Pan, Min Liu, Wei Wang, Yuehan Zhao
                 */
                /**
                 * This part is used to check whether player collides with enemy's planes, player's bullets hit enemies or enemies' bullets hit player
                 * if player hits enemy, enemy health will decrease. If health=0, enemy explodes and player get scores
                 * if enemy hits player, player's life will decrease. If life=0, game over.
                 * if crash, game over.
                 */

                for (ArrayList<Enemy> enemy : enemies){
                    crash(enemy);
                    player_hit_enemy(enemy);
                }
                for (ArrayList<Bullet> bullets:enemybullets){
                    enemy_hit_player(bullets);
                }

                /**
                 *  Created by Yuehan Zhao, Min Liu
                 */
                /**
                 * This part is used to check whether the player get the award
                 * if player get health award, life increase by 1
                 * if player get power up award, change bullet type.
                 */
                for(int i = 0; i< PowerupAward.size(); i++){
                    Award award= PowerupAward.get(i);
                    if (gameplayer.getAward(award)){
                        PowerupAward.remove(award);
                        soundPool.play(soundID.get(6), 1, 1,0,0, 1.0f );   // play sound
                        getpoweraward =true;
                    }else {award.drawself(gameCanvas,paint); }
                }
                for(int i=0;i<HealthAward.size();i++){
                    Award award=HealthAward.get(i);
                    if (gameplayer.getAward(award)){
                        HealthAward.remove(award);
                        soundPool.play(soundID.get(6), 1, 1,0,0, 1.0f );   // play sound
                        award.makeLifeUp(gameplayer);
                    }else {award.drawself(gameCanvas,paint); }
                }

                Thread.sleep(10);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                gameHolder.unlockCanvasAndPost(gameCanvas);
            }
        }


    }

    @Override
    public void run() {


        while (gamerunning) {
            draw();
            gamelogic();

        }
        }
    /**
     * created by Min Liu
     */
    /**
     * if the mouse is down, get the coordinate of player and mouse.
     * Moving the player based on the mouse movement.
     *
     * The idea of how to get the coordinate of the player in real time is inspired by
     * <https://stackoverflow.com/questions/14814542/moving-imageview-with-touch-event>
     */
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                clickX = motionEvent.getX();
                clickY = motionEvent.getY();
                playerX = gameplayer.getX();
                playerY = gameplayer.getY();
                differentX = clickX - playerX;
                differentY = clickY - playerY;
                break;

            case MotionEvent.ACTION_MOVE:
                gameplayer.setX(motionEvent.getX() - differentX);
                gameplayer.setY(motionEvent.getY() - differentY);

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;
    }
}





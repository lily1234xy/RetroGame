package test;

import com.example.u6485142.retrogame2018s1.Award;
import com.example.u6485142.retrogame2018s1.Bullet;
import com.example.u6485142.retrogame2018s1.Enemy;
import com.example.u6485142.retrogame2018s1.Player;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  Created by Min Liu
 */

public class LifeTest {
    Player player = new Player(50,100,5);
    Enemy smallEnemy = new Enemy(50,50, Enemy.EnemyType.small);
    Enemy middleEnemy = new Enemy(50,50, Enemy.EnemyType.middle);
    Enemy largeEnemy = new Enemy(50,50, Enemy.EnemyType.large);
    Bullet smallBullet = new Bullet(50, 100, Bullet.BulletType.bullet_enemy_small);
    Bullet playerBullet = new Bullet(50, 50, Bullet.BulletType.bullet_player);
    Award healthAward = new Award(50, 100, Award.AwardType.health_award);

    @Test
    public void playerLifeReduce() {
        boolean collide = smallBullet.Collide_player(player);
        boolean b = false;
        if (collide && player.lifeRemain(smallBullet) == 4) {
            b = true;
        } else if (!collide && player.getLife() == 5)
            b = true;

        assertTrue("The remaining life should be 4 but got: " + player.getLife(), b);
    }

    @Test
    public void enemyLifeReduce() {
        boolean collide = playerBullet.Collide_enemy(middleEnemy);
        boolean b = false;
        if (collide){
            middleEnemy.attacked(playerBullet);
            assertEquals("The remaining life should be 1 but got: " + middleEnemy.getHealth(), 1, middleEnemy.getHealth());

        } else
            assertEquals("The remaining life should be 2 but got: " + middleEnemy.getHealth(), 2, middleEnemy.getHealth());
    }

    @Test
    public void LifeIncrease() {
        player.setLife(4);
        if (player.getAward(healthAward)) {
            healthAward.makeLifeUp(player);
            assertEquals("The life of the player should be 5 but got: " + player.getLife(), 5, player.getLife());
        } else
            assertEquals("The life of the player should be 4 but got: " + player.getLife(), 4, player.getLife());

    }

    @Test
    public void LifeNotIncrease() {
        if (player.getAward(healthAward)) {
            healthAward.makeLifeUp(player);
            assertEquals("The life of the player should be 5 but got: " + player.getLife(), 5, player.getLife());
        }

    }

}
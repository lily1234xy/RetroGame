package test;

import com.example.u6485142.retrogame2018s1.Enemy;
import com.example.u6485142.retrogame2018s1.Player;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {
    Enemy smallEnemy = new Enemy(50,50, Enemy.EnemyType.small);
    Enemy middleEnemy = new Enemy(50,50, Enemy.EnemyType.middle);
    Enemy largeEnemy = new Enemy(50,50, Enemy.EnemyType.large);
    Enemy boss = new Enemy(50,50, Enemy.EnemyType.boss);
    Enemy bossSoldier = new Enemy(50,50, Enemy.EnemyType.bossoldier);
    Player player = new Player(50,100,5);



    @Test
    public void smallEnemyScore() {
        smallEnemy.destroyed = true;
        player.setScore(smallEnemy.getScore());

        assertEquals("The score of large enemy should be 1 but got: " + player.getScore(), 1, player.getScore());
    }
    @Test
    public void middleEnemyScore() {
        middleEnemy.destroyed = true;
        player.setScore(middleEnemy.getScore());

        assertEquals("The score of large enemy should be 5 but got: " + player.getScore(), 5, player.getScore());
    }

    @Test
    public void largeEnemyScore() {
        largeEnemy.destroyed = true;
        player.setScore(largeEnemy.getScore());

        assertEquals("The score of large enemy should be 10 but got: " + player.getScore(), 10, player.getScore());
    }

    @Test
    public void bossScore() {
        boss.destroyed = true;
        player.setScore(boss.getScore());

        assertEquals("The score of large enemy should be 1000 but got: " + player.getScore(), 1000, player.getScore());
    }

    @Test
    public void bossSoldierScore() {
        bossSoldier.destroyed = true;
        player.setScore(bossSoldier.getScore());

        assertEquals("The score of large enemy should be 50 but got: " + player.getScore(), 50, player.getScore());
    }

    @Test
    public void getScore() {
        smallEnemy.destroyed = true;
        player.setScore(1);
        boolean b = false;
        if (player.getScore() == 1)
            b = true;

        assertTrue("The score of large enemy should be 1 but got: " + player.getScore(), b);
    }
}
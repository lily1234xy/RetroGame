package test;

import com.example.u6485142.retrogame2018s1.Bullet;
import com.example.u6485142.retrogame2018s1.Enemy;
import com.example.u6485142.retrogame2018s1.Player;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
public class CollisionTest {

    Bullet bullet = new Bullet(50, 50, Bullet.BulletType.bullet_player);
    Enemy enemy=new Enemy(50,50, Enemy.EnemyType.small);
    Enemy largeenemy = new Enemy(50,50, Enemy.EnemyType.large);
    Player player = new Player(50,50,5);





    @Test
    public void BulletCollideEnemy() {
        //bullet.move();
        boolean collision = bullet.Collide_enemy(enemy);
        assertTrue("Bullet didn't hit the enemy",collision);
    }

    @Test
    public void BulletDestroyEnemy() {
        enemy.attacked(bullet);
        assertTrue("Enemy was not destroyed",enemy.checkDestroyed());
    }

    @Test
    public void BulletHitEnemy() {
        largeenemy.attacked(bullet);
        assertEquals("Enemy health didn't decrease properly",2, largeenemy.health);
    }
    @Test
    public void BulletMissEnemy(){
        bullet.move();
        assertFalse("Bullet accidentally hit the Enemy",bullet.Collide_enemy(enemy));
    }
}
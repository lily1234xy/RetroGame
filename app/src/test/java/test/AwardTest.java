package test;

import com.example.u6485142.retrogame2018s1.Award;
import com.example.u6485142.retrogame2018s1.Player;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Yuehan Zhao
 */

/**
 * This class is used to test awards.
 */
public class AwardTest {

     /* set new health award and power up award*/
    Award health_award=new Award(50,50, Award.AwardType.health_award);
    double current_health_award_y =health_award.getY();
    Award powerup_award=new Award(50,50, Award.AwardType.powerup_award);
    double current_powerup_award_y =powerup_award.getY();
    Player player=new Player(50,50,5);




    /*Test the award's move function*/
    @Test
    public void AwardMoveTest(){
        health_award.move();
        assertTrue("The health award should move",health_award.getY()!= current_health_award_y);
        powerup_award.move();
        assertTrue("The bullet_award should move",powerup_award.getY()!= current_powerup_award_y);
    }
    /*Test the player's get award function*/
    @Test
    public void getAwardTest(){

        assertTrue("Player should get the health award!",player.getAward(health_award));
        assertTrue("Player should get the bullet award!",player.getAward(powerup_award));

        health_award.setX(0);
        health_award.setY(0);
        powerup_award.setX(100);
        powerup_award.setY(100);
        assertFalse("Player shouldn't get the health award!",player.getAward(health_award));
        assertFalse("Player shouldn't get the bullet award",player.getAward(powerup_award));

    }

    /*Test if the health award can increase player's life by 1.*/
    @Test
    public void getHealthAwardTest(){
        health_award.setX(50);
        health_award.setY(50);
        player.setLife(4);
        int previous_life =player.getLife();

        if(player.getAward(health_award)) {
            health_award.makeLifeUp(player);
            assertTrue("Life of player should increase 1", player.getLife() - previous_life == 1);
        }
        player.setLife(5);
        if(player.getAward(health_award)) {
            health_award.makeLifeUp(player);
            assertFalse("Life of player shouldn't increase 1 because it's full life", player.getLife() == previous_life );
        }

        health_award.setX(0);
        health_award.setY(0);
        player.setLife(5);
        if(player.getAward(health_award)) {
            health_award.makeLifeUp(player);
            assertTrue("Life of player shouldn't increase", player.getLife() - previous_life == 0);
        }
    }
}

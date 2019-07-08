# Testing Summary
_Tests written by Yuehan Zhao, Min liu, Tingyu Pan and Wei Wang_

## Testing Contents

AwardTest: In AwardTest, I test whether the Award class works well.
Firstly, I test whether these two awards can move. Secondly I test if the player can get award
by setting the x and y location. Thirdly I test if the player can increase the life or not when get
health award with not full life and full life.

Collision Test:Under the Collision Test. The BulletCollideEnemy checked if the bullet attack the
enemies with the right return boolean at the same locations. The BulletDetroyedEnemy check the
one-hit death smallplane destroyed status to assure bullet can kill enemies. The BulltHitEnemy check
 if the enemy count the health refer to the bullet damage upon a collision. The BulletMissEnemy
 move the bullet a bit out of the bitmap of enemy to check the status staying as the initial one,
 which is not colliding with a false.

LifeTest: Testing whether the player and enemy's life works well. The playerLifeReduce() method 
tests whether the player's life will reduce when hit by small bullet. The enemyLifeReduce() method
tests whether the enemy's life will reduce when hit by player bullet. The LifeIncrease() method and
LifeNotIncrease() method test whether the player's life can increase by eating health award.

ScoreTest: Testing whether destroying enemy plane will increase the score, and test each enemy's
score. The getScore() method tests whether the getScore() method in Player class works well.

## Testing Results

All tests passed.


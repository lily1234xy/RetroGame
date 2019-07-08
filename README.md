## Team structure and roles
+ Wei Wang - team leader, morale builder, build initial frame, user input, debug
+ Yuehan Zhao - tricky android code, game state, help page code, game graphics rendering, modified all code and provide assistance.
+ Min Liu  - tricky android code, note taker, user input documentation checker
+ Tingyu Pan - note taker, documentation checker, tricky android code

## Game Summary
![Image of Screenshoot](https://gitlab.cecs.anu.edu.au/u6485142/RetroGame2018s1/raw/master/app/src/main/Screenshot.png)

_This game was built by Android studio and running on Android 7.0+. 
Raiden is a retro shooting game. Our Raiden game is developed based on the basic function of the retro game.
What you need to do in this game is to shoot enemy planes to get a good score, and avoid being hit by bullets and enemy planes.
You can use finger to touch on and move the player plane. The plane you controlled will automatically shoot.
The enemy planes will come out from the top of the screen and shoot. Red award can double your bullets for a short time, if you lose your HP, eating green award will add 1 HP to your plane. Be careful, colliding with enemy planes will lead to game over._

## Design Documents
+ [Design Summary](https://gitlab.cecs.anu.edu.au/u6485142/RetroGame2018s1/blob/master/app/src/design%20summary.md)
+ [UML Diagram](https://gitlab.cecs.anu.edu.au/u6485142/RetroGame2018s1/blob/master/app/src/RaidenGameUML.xmi)
+ [Testing Summary](https://gitlab.cecs.anu.edu.au/u6485142/RetroGame2018s1/blob/master/app/src/testing%20summary.md)

## Meeting Minutes
+ [Meeting 1 - 22/4 - decide team structure and game](https://gitlab.cecs.anu.edu.au/u6485142/RetroGame2018s1/blob/master/app/src/meeting1.docx)
+ [Meeting 2 - 26/4 - decide functions and deal with issues](https://gitlab.cecs.anu.edu.au/u6485142/RetroGame2018s1/blob/master/app/src/meeting2.docx)
+ [Meeting 3 - 11/5 - consider and plan extension aspects](https://gitlab.cecs.anu.edu.au/u6485142/RetroGame2018s1/blob/master/app/src/meeting3.docx)
+ [Meeting 4 - 16/5 - review and practice demo](https://gitlab.cecs.anu.edu.au/u6485142/RetroGame2018s1/blob/master/app/src/meeting4.docx)

### Inspiration
_The idea of how to get the coordinate of the player in real time 
is inspired by <https://stackoverflow.com/questions/14814542/moving-imageview-with-touch-event>_

_this part use android.graphics.Rect inspired by <https://developer.android.com/reference/android/graphics/Rect>_

_The idea of how to use surfaceview in GameView.java is inspired by <https://github.com/HurTeng/StormPlane>_

_the way to set the MainActivity inspired by <https://stackoverflow.com/questions/15338769/issues-with-creating-new-android-mainactivity?noredirect=1&lq=1>_

### Code

_The code of adding sound into the game view is based on the soundpool 
code from: <https://blog.csdn.net/gophers/article/details/38513255>_

### Assets

_The sound and BGM of this game is from: <https://github.com/HurTeng/StormPlane/tree/master/app/src/main/res/raw>_

_The bitmap of all the bullets, the player plane are downloaded from: <https://github.com/HurTeng/StormPlane>_

_The bitmap of the enemy planes, boss and explosion are coming from: <https://github.com/pedrorfernandes/raiden/tree/raiden/assets>_

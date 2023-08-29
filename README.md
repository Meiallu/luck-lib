# Luck
Framework focused on making game development easy and fast on Java.

Luck was made with the idea of making game development extremely hassle-free. In an extremely quick way to setup, you have the power to change the Framework's code whenever you want, however you want, without much difficulty. The tools are there, you just need to use them. Feel free to optimize, remove, add functions and framework systems.

# Setup
Download the packages from the releases page, then extract them into the "/src" of your project. After that, you will need to create a game instance with ```new Instance("title", width, height);```, this will create the window, the canvas, an initial scene (which you can change) and several others essential systems. And that's it!
```java
// This creates the Window, Canvas and Base scene.
new Instance("Title", 320, 180);

// This creates the Animation and Sprite Objects.
Animation run = new Animation();
run.setupFrames("./images/", "run", ".png");
Sprite playerSprite = new Sprite(run);

// This creates a Object type
// And then creates an instance of it.
Object Player = new Object(playerSprite);
Player.create(10, 10);
```
Oh, you want to easily play an audio? well, there you go:
```java
Audio myWav = new Audio("./audios/test.wav");
myWav.play();
```

There's no limits.
Please fell free to change the framework's code and commit it. I'm open to feedbacks.

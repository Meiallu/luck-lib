# Luck

Library focused on making game development easy and fast on Java.

Luck was made with the idea of making game development extremely hassle-free. In an extremely quick way to setup.
Feel free to optimize, remove, add functions and library systems.

# Setup

Download the packages, then extract them into the "/src" of your project. After that, you will
need to create a game instance with ```new Instance("title", width, height);```, this will create the window, the
canvas, an initial scene (which you can change) and several others essential systems. And that's it!

```java
// This creates the Window, Canvas and Scene.
new Instance("Title", 320, 180);

// This creates the Sprite.
Sprite spr_player = new Sprite();
spr_player.setupFrames("images/", "idle", ".png");

// This creates a Object type
// And then creates an instance of it.
Object obj_player = new Object(spr_player);
obj_player.create(10, 10);
```

Oh, you want to easily play an audio? well, there you go:

```java
Audio theme = new Audio("audios/theme.wav");
theme.play();
```

Please fell free to change the framework's code and commit it. I'm open to feedbacks.

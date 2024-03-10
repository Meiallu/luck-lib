NOT READY FOR USAGE. USE IT AT YOUR OWN RISK.

# Luck

Library focused on making game development easy and fast on Java.

Luck was made with the idea of making game development extremely hassle-free. In an extremely quick way to setup.
Feel free to optimize, remove, add functions and library systems.

# Setup

Download the packages on the releases page, extract them into the "/src" of your project. Then, you'll need to create
a game instance with ```new Instance("title", width, height);```, this will create the window, the
canvas, an initial scene (which you can change) and several others essential systems. And that's it!

# Example

```java
// This creates the Window, Canvas and Scene.
new Instance("Title",320,180);

// This creates the Sprite.
Sprite spr_player = new Sprite();
spr_player.setupFrames("images/","idle",".png");

// This creates a Object type
// And then creates an instance of it.
Object obj_player = new Object(spr_player);
obj_player.create(10,10);
```

Please fell free to clone, change and commit it. I'm open to feedbacks.

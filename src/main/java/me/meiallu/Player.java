package me.meiallu;

import org.luck.listener.Luck;
import org.luck.system.Camera;
import org.luck.system.Canvas;
import org.luck.type.Object;
import org.luck.type.Sprite;
import org.luck.util.Keyboard;
import org.luck.util.Util;

public class Player extends Luck {
    Object obj;
    Object solid;

    float speed = 1;
    float xv = 0;
    float yv = 0;

    Sprite idle;
    Sprite run;
    Sprite roll;

    @Override
    public void start() {
        Canvas.setType(Canvas.LETTERBOX);
        Canvas.setLetterboxPrecision(1);

        idle = new Sprite();
        run = new Sprite();
        roll = new Sprite();

        idle.setupFrames("images/idle/", "idle", ".png");
        run.setupFrames("images/run/", "run", ".png");
        roll.setupFrames("images/roll/", "roll", ".png");

        idle.setSpeed(9);
        run.setSpeed(12);
        roll.setSpeed(11);

        Object type = new Object(idle);
        obj = type.create(100, 90);
        obj.setOrigin(11, 23);

        Sprite urlsprite = new Sprite();
        urlsprite.addFrame(Util.getImageURL("https://imgs.search.brave.com/M8D2kvnfl2ctGbbIvzj9g-WLvP3E8atO01KmZT94mjk/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9pLmt5/bS1jZG4uY29tL3Bo/b3Rvcy9pbWFnZXMv/bGlzdC8wMDIvNjM2/LzU4MS9hMWUuanBn"));
        solid = new Object(urlsprite);
        solid.create(200, 100);
    }

    @Override
    public void update() {
        if (Keyboard.isPressed('d')) {
            xv = 1;
            obj.setScaleX(1);
        } else if (Keyboard.isPressed('a')) {
            xv = -1;
            obj.setScaleX(-1);
        } else {
            xv = 0;
        }

        if (Keyboard.isPressed('w')) {
            yv = -1;
        } else if (Keyboard.isPressed('s')) {
            yv = 1;
        } else {
            yv = 0;
        }

        if (yv != 0 && xv != 0) speed = 0.75f;
        else speed = 1;

        if ( !obj.isPlaceMeeting(xv * speed, 0, solid) )
            obj.setX(obj.getX() + xv * speed);
        if ( !obj.isPlaceMeeting(0, yv * speed, solid) )
            obj.setY(obj.getY() + yv * speed);

        if (obj.getSprite() != roll) {
            if (xv == 0 && yv == 0)
                obj.setSprite(idle);
            else
                obj.setSprite(run);
        }
    }
}
package me.meiallu;

import org.luck.listener.Luck;
import org.luck.type.Animation;
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

    Animation idle;
    Animation run;
    Animation roll;

    @Override
    public void start() {

        // -------------------------------------------
        // Player setup
        // -------------------------------------------

        idle = new Animation();
        run = new Animation();
        roll = new Animation();

        idle.setupFrames("images/idle/", "idle", ".png");
        run.setupFrames("images/run/", "run", ".png");
        roll.setupFrames("images/roll/", "roll", ".png");

        idle.setSpeed(9);
        run.setSpeed(12);
        roll.setSpeed(11);

        Sprite Player = new Sprite(idle);
        Player.addAnimation(run);
        Player.addAnimation(roll);

        Object Type = new Object(Player);
        obj = Type.create(100, 90);
        obj.setOrigin(22, 0);

        // -------------------------------------------
        // Custom URL image system
        // -------------------------------------------

        Animation urlanim = new Animation();
        urlanim.addFrame(Util.getImageURL("https://imgs.search.brave.com/M8D2kvnfl2ctGbbIvzj9g-WLvP3E8atO01KmZT94mjk/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9pLmt5/bS1jZG4uY29tL3Bo/b3Rvcy9pbWFnZXMv/bGlzdC8wMDIvNjM2/LzU4MS9hMWUuanBn"));
        Sprite urlsprite = new Sprite(urlanim);
        solid = new Object(urlsprite);
        solid.create(200, 100);
    }

    @Override
    public void update() {
        if (Keyboard.isPressed('d')) {
            xv = 1;
            obj.setMirrored(false);
        } else if (Keyboard.isPressed('a')) {
            xv = -1;
            obj.setMirrored(true);
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

        if (!obj.isPlaceMeeting(xv * speed, 0, solid))
            obj.setX(obj.getX() + xv * speed);
        if (!obj.isPlaceMeeting(0, yv * speed, solid))
            obj.setY(obj.getY() + yv * speed);

        if (obj.getAnimation() != roll) {
            if (xv == 0 && yv == 0) obj.setAnimation(idle);
            else obj.setAnimation(run);
        }
    }
}
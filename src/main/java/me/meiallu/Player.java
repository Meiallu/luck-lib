package me.meiallu;

import org.luck.listener.Luck;
import org.luck.system.nes.Game;
import org.luck.system.type.Animation;
import org.luck.system.type.Sprite;
import org.luck.util.Keyboard;
import org.luck.system.type.Object;

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
		obj = Type.create(160, 90);

        // -------------------------------------------
        // Solid setup
        // -------------------------------------------

        Animation def = new Animation();
        def.addFrame("images/solid.png");

        Sprite spr = new Sprite(def);

        solid = new Object(spr);
        solid.setSize(64, 64);
        solid.create(200, 100);

        while ( solid.getChilds().size() < 200000 ) {
            solid.create(1000, 1000);
        }
        solid.destroy();
    }

    @Override
    public void update() {
		if ( Keyboard.isPressed('d') ) {
            xv = 1;
			obj.setMirrored(false);
        } else if ( Keyboard.isPressed('a') ) {
            xv = -1;
			obj.setMirrored(true);
        } else { xv = 0; }

		if ( Keyboard.isPressed('w') ) { yv = -1; } 
        else if ( Keyboard.isPressed('s') ) { yv = 1; } 
        else { yv = 0; }

        if (yv != 0 && xv != 0) speed = 0.75f;
        else speed = 1;
        
        if ( !obj.isPlaceMeeting(xv * speed, 0, solid) )
            obj.setX( (float) obj.getX() + xv * speed);
        if ( !obj.isPlaceMeeting(0, yv * speed, solid) )
            obj.setY( (float) obj.getY() + yv * speed);

        if (obj.getAnimation() != roll) {
            if (xv == 0 && yv == 0) obj.setAnimation(idle);
            else obj.setAnimation(run);
        }
    }
}
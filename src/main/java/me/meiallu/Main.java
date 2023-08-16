package me.meiallu;

import org.luck.listener.onTick;
import org.luck.system.nes.Instance;
import org.luck.system.type.Animation;
import org.luck.system.type.Object;
import org.luck.system.type.Sprite;
import org.luck.util.Keyboard;

public class Main extends onTick {
	Object Instance;

    public Main() {
		new Instance("Bullet Hellween", 320, 180);

		Animation idle = new Animation();
		idle.setupFrames("images/run/", "run", ".png");
		Sprite Player = new Sprite(idle);

		Object Type = new Object(Player);
		Instance = Type.create(160, 90);
		Instance.setSpeed(12.0f);
    }

	@Override
	public void everyTick() {
		if ( Keyboard.isPressed('d') ) Instance.setX( Instance.getX() + 1 );
		if ( Keyboard.isPressed('a') ) Instance.setX( Instance.getX() - 1 );
		if ( Keyboard.isPressed('w') ) Instance.setY( Instance.getY() - 1 );
		if ( Keyboard.isPressed('s') ) Instance.setY( Instance.getY() + 1 );
	}
}
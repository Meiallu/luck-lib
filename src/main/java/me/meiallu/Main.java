package me.meiallu;

import org.luck.listener.onTick;
import org.luck.system.nes.Instance;
import org.luck.system.type.Animation;
import org.luck.system.type.Object;
import org.luck.system.type.Sprite;
import org.luck.util.Keyboard;

public class Main extends onTick {
	public Object ins;

    public Main() {
		new Instance("Bullet Hellween", 320, 180);

		Animation idle = new Animation();
		idle.setSpeed(12.0f);
		idle.setupFrames("images/run/", "run", ".png");
		Sprite Player = new Sprite(idle);

		Object Type = new Object(Player);
		ins = Type.create(160, 90);
    }

	@Override
	public void everyTick() {
		if ( Keyboard.isPressed('d') ) {
			ins.setX( ins.getX() + 1 );
			ins.setMirrored(false);
		}
		if ( Keyboard.isPressed('a') ) {
			ins.setX( ins.getX() - 1 );
			ins.setMirrored(true);
		}
		if ( Keyboard.isPressed('w') ) {
			ins.setY( ins.getY() - 1 );
		}
		if ( Keyboard.isPressed('s') ) {
			ins.setY( ins.getY() + 1 );
		}
	}
}
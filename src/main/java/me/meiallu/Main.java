package me.meiallu;

import org.luck.listener.onTick;
import org.luck.system.nes.Instance;
import org.luck.system.type.Animation;
import org.luck.system.type.Object;
import org.luck.system.type.Sprite;
import org.luck.util.Keyboard;

public class Main extends onTick {
	Object Jogador;

    public Main() {
		new Instance("Bullet Hellween", 320, 180);

		Animation idle = new Animation();
		idle.setupFrames("images/run/", "run", ".png");
		Sprite Player = new Sprite(idle);

		Object Type = new Object(Player);
		Jogador = Type.create(160, 90);
		Type.setSpeed(12.0f);
    }

	@Override
	public void everyTick() {
		if ( Keyboard.isPressed('d') ) Jogador.setX( Jogador.getX() + 1 );
		if ( Keyboard.isPressed('a') ) Jogador.setX( Jogador.getX() - 1 );
		if ( Keyboard.isPressed('w') ) Jogador.setY( Jogador.getY() - 1 );
		if ( Keyboard.isPressed('s') ) Jogador.setY( Jogador.getY() + 1 );
	}
}
package me.meiallu;

import java.awt.Color;
import java.awt.Font;

import org.meiallu.system.Instance;
import org.meiallu.system.type.Animation;
import org.meiallu.system.type.Object;
import org.meiallu.system.type.Sprite;
import org.meiallu.util.Text;

public class Main {
	
	public static void main(String[] args) {
		new Instance("Bullet Hellween", 320, 180);

		Animation idle = new Animation();
		idle.setupFrames("run", "run", ".png");
		Sprite Player = new Sprite(idle);

		Object Jogador = new Object(Player);
		Jogador.setSpeed(12.0f);
		Jogador.create(160, 90);
	}
}
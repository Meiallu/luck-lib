package me.meiallu;

import java.awt.Color;

import org.luck.listener.Luck;
import org.luck.system.nes.Instance;

public class Main extends Luck {

	@Override
	public void start() {
		new Instance("Evolunhoca", 533, 266);
		new Player();
		Instance.getPanel().setBackground(Color.BLACK);
	}

	@Override
	public void update() {}
}
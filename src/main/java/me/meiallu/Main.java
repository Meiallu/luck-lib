package me.meiallu;

import java.awt.Color;
import org.luck.system.nes.Instance;

public class Main {

	public Main() {
		new Instance("Evolunhoca", 533, 266);
		new Player();
		Instance.getPanel().setBackground(Color.BLACK);
	}
}
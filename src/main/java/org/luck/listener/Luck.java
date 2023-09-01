package org.luck.listener;

import org.luck.system.Instance;
import org.luck.util.Device;
import org.luck.util.Keyboard;

import java.security.Key;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("unused")
public abstract class Luck {

    public Luck() {
        start();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, (1000 / Device.refreshRate));
    }

    public abstract void start();
    public abstract void update();
}

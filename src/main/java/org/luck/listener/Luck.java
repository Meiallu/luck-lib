package org.luck.listener;

import org.luck.util.Device;

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
        }, 0, Device.refreshTime);
    }

    public abstract void start();

    public abstract void update();
}

package org.luck.listener;

import java.util.Timer;
import java.util.TimerTask;

import org.luck.util.Device;

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

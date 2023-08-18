package org.luck.listener;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Luck {
    
    public Luck() {     
        start();   
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, 16);
    }

    public abstract void start();
    public abstract void update();
}

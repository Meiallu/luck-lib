package org.luck.listener;

import java.util.Timer;
import java.util.TimerTask;

public abstract class onTick {
    
    public onTick() {        
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                everyTick();
            }
        }, 0, 16);
    }

    public abstract void everyTick();
}

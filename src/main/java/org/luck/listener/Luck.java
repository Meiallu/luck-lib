package org.luck.listener;

import org.luck.util.Device;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public abstract class Luck {

    public Luck() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
                this::update,
                Device.refreshTime, Device.refreshTime,
                TimeUnit.MILLISECONDS
        );
        start();
    }

    public abstract void start();
    public abstract void update();
}

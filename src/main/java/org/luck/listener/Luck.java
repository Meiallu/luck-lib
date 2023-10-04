package org.luck.listener;

import org.luck.util.Device;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public abstract class Luck {

    public Luck() {
        start();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::update, Device.refreshTime, Device.refreshTime, TimeUnit.MILLISECONDS);
    }

    public abstract void start();
    public abstract void update();
}

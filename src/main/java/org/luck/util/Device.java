package org.luck.util;

import java.awt.*;

@SuppressWarnings("unused")
public class Device {

    public static final int refreshRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();
    public static final int refreshTime = 1000 / refreshRate;
}

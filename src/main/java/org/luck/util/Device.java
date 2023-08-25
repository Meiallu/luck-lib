package org.luck.util;

import java.awt.GraphicsEnvironment;

public class Device {
    public static final int refreshRate = GraphicsEnvironment
                                            .getLocalGraphicsEnvironment()
                                            .getScreenDevices()[0]
                                            .getDisplayMode()
                                            .getRefreshRate();
}
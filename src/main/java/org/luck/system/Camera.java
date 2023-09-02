package org.luck.system;

import java.awt.*;

@SuppressWarnings("unused")
public class Camera {
    private static Dimension viewport = new Dimension();
    private static double xreal = 0;
    private static double yreal = 0;
    private static float x = 0;
    private static float y = 0;

    public static double getRealX() { return xreal; }
    public static double getRealY() { return yreal; }

    public static Dimension getView() { return viewport; }
    public static float getViewX() { return (float) viewport.getWidth(); }
    public static float getViewY() { return (float) viewport.getHeight(); }

    public static float getX() { return x; }
    public static float getY() { return y; }

    public static void setX(float xx) {
        x = xx;
        xreal = xx - (viewport.getWidth() / 2);
    }

    public static void setY(float yy) {
        y = yy;
        yreal = yy - (viewport.getHeight() / 2);
    }
}

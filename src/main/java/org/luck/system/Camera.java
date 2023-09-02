package org.luck.system;

import java.awt.*;

@SuppressWarnings("unused")
public class Camera {
    private static Dimension viewport = new Dimension();
    private static double xreal = 0;
    private static double yreal = 0;
    private static double x = 0;
    private static double y = 0;

    public static double getRealX() { return xreal; }
    public static double getRealY() { return yreal; }

    public static Dimension getView() { return viewport; }
    public static double getViewX() { return viewport.getWidth(); }
    public static double getViewY() { return viewport.getHeight(); }

    public static double getX() { return x; }
    public static double getY() { return y; }

    public static void setX(double xx) {
        x = xx;
        xreal = xx - (viewport.getWidth() / 2);
    }

    public static void setY(double yy) {
        y = yy;
        yreal = yy - (viewport.getHeight() / 2);
    }
}

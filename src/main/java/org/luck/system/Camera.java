package org.luck.system;

import org.luck.util.Vector2D;

import java.awt.*;

@SuppressWarnings("unused")
public class Camera {

    private static Dimension viewport = new Dimension();
    private static double x = 0;
    private static double y = 0;

    public static Dimension getViewport() { return viewport; }

    public static double getViewX() { return viewport.getWidth(); }

    public static double getViewY() { return viewport.getHeight(); }

    public static double getX() { return x; }

    public static void setX(double xx) { x = xx; }

    public static double getY() { return y; }

    public static void setY(double yy) { y = yy; }

    public static Vector2D getLocation() {
        return new Vector2D(x, y);
    }

    public static void setLocation(double xx, double yy) {
        x = xx;
        y = yy;
    }
}

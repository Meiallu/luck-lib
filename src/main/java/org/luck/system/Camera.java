package org.luck.system;

@SuppressWarnings("unused")
public class Camera {
    private static float xreal = 0;
    private static float yreal = 0;
    private static float x = 0;
    private static float y = 0;

    public static float getRealX() { return xreal; }
    public static float getRealY() { return yreal; }

    public static float getX() { return x; }
    public static float getY() { return y; }

    public static void setX(float xx) {
        x = xx;
        xreal = xx - (Instance.getWidth() / 2);
    }

    public static void setY(float yy) {
        y = yy;
        yreal = yy - (Instance.getHeight() / 2);
    }
}

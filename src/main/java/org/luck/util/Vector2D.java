package org.luck.util;

@SuppressWarnings("unused")
public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public Vector2D setX(float x) {
        this.x = x;
        return this;
    }

    public Vector2D setY(float y) {
        this.y = y;
        return this;
    }
}
package org.luck.util;

public class Vector2D {
    private float x = 0;
    private float y = 0;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    
    public float getX() { return x; }
    public float getY() { return y; }
}
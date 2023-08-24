package org.luck.util;

public class Vector2D {
    private int width = 0;
    private int height = 0;

    public Vector2D(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}

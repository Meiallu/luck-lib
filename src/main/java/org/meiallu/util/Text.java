package org.meiallu.util;

import java.awt.Color;
import java.awt.Font;

import org.meiallu.system.Game;
import org.meiallu.system.Layer;
import org.meiallu.system.Scene;

public class Text {
    private int x;
    private int y;
    private float opacity = 1.0f;
    private boolean visible = true;
    private boolean offsetable = true;
    private Color color = Color.BLACK;
    private int style = Font.PLAIN;
    private String font = "SansSerif";
    private int size = 12;
    private String text = "";

    public Text(String string, int xx, int yy) {
        x = xx;
        y = yy;
        text = string;
        Game.getScene().getLayers().get(0).getTexts().add(this);
    }

    public Text(String string, int xx, int yy, Scene scene) {
        x = xx;
        y = yy;
        text = string;
        scene.getLayers().get(0).getTexts().add(this);
    }

    public Text(String string, int xx, int yy, Layer lay) {
        x = xx;
        y = yy;
        text = string;
        lay.getTexts().add(this);
    }

    public Text(String string, int xx, int yy, Scene scene, Layer lay) {
        x = xx;
        y = yy;
        text = string;
        lay.getTexts().add(this);
    }

    public int getX() { return x; }
    public void setX(int xx) { x = xx; }

    public int getY() { return y; }
    public void setY(int yy) { y = yy; }

    public float getOpacity() { return opacity; }
    public void setOpacity(float i) { opacity = i; }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean i) { visible = i; }

    public String getText() { return text; };
    public void setText(String str) { text = str; }

    public int getSize() { return size; };
    public void setSize(int i) { size = i; }

    public String getFont() { return font; };
    public void setFont(String str) { font = str; }

    public Color getColor() { return color; };
    public void setColor(Color col) { color = col; }

    public int getStyle() { return style; };
    public void setStyle(int styl) { style = styl; }

    public boolean isOffsetable() { return offsetable; }
    public void setOffsetable(boolean i) { offsetable = i; } 
}

package org.luck.system.type;

import java.awt.Color;
import java.awt.Font;

import org.luck.system.nes.Game;
import org.luck.system.nes.Layer;
import org.luck.system.nes.Scene;

public class Text {
    private float x;
    private float y;
    private float opacity = 1.0f;
    private boolean visible = true;
    private boolean offsetable = true;
    private Color color = Color.BLACK;
    private byte style = Font.PLAIN;
    private String font = "SansSerif";
    private short size = 12;
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

    public float getX() { return x; }
    public void setX(int xx) { x = xx; }

    public float getY() { return y; }
    public void setY(int yy) { y = yy; }

    public float getOpacity() { return opacity; }
    public void setOpacity(float i) { opacity = i; }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean i) { visible = i; }

    public String getText() { return text; };
    public void setText(String str) { text = str; }

    public int getSize() { return size; };
    public void setSize(int i) { size = (short) i; }

    public String getFont() { return font; };
    public void setFont(String str) { font = str; }

    public Color getColor() { return color; };
    public void setColor(Color col) { color = col; }

    public int getStyle() { return style; };
    public void setStyle(int styl) { style = (byte) styl; }

    public void setLayer(Layer l) {
        getLayer().getTexts().remove(this);
        l.getTexts().add(this);
    }

    public void setLayer(Layer l, int index) {
        getLayer().getTexts().remove(this);
        l.getTexts().add(index, this);
    }

    public void setLayer(Layer l, Scene scene) {
        getLayer(scene).getTexts().remove(this);
        l.getTexts().add(this);
    }

    public void setLayer(Layer l, int index, Scene scene) {
        getLayer(scene).getTexts().remove(this);
        l.getTexts().add(index, this);
    }

    public Layer getLayer() {
        for ( Layer l : Game.getScene().getLayers() )
            if ( l.getTexts().contains(this) ) {
                return l;
            }
        return null;            
    }

    public Layer getLayer(Scene scene) {
        for ( Layer l : scene.getLayers() )
            if ( l.getTexts().contains(this) ) {
                return l;
            }
        return null;            
    }

    public void setZ(int index) {
        Layer l = getLayer();
        int bf = l.getTexts().indexOf(this);
        l.getTexts().add(index, this);
        l.getTexts().remove(bf);
    }

    public void setZ(int index, Scene scene) {
        Layer l = getLayer(scene);
        int bf = l.getTexts().indexOf(this);
        l.getTexts().add(index, this);
        l.getTexts().remove(bf);
    }

    public int getZ() { return getLayer().getTexts().indexOf(this); }
    public int getZ(Scene scene) { return getLayer(scene).getTexts().indexOf(this); }

    public boolean isOffsetable() { return offsetable; }
    public void setOffsetable(boolean i) { offsetable = i; } 
}

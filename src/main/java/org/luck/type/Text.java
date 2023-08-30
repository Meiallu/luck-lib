package org.luck.type;

import org.luck.system.Game;
import org.luck.system.Layer;
import org.luck.system.Scene;

import java.awt.*;

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
    private String text;
    private int ID;

    public Text(String string, int x, int y) {
        Game.getScene().lastID++;
        ID = Game.getScene().lastID;
        this.x = x;
        this.y = y;
        text = string;
        Game.getScene().getLayers().get(0).getTexts().add(this);
    }

    public Text(String string, int x, int y, Scene scene) {
        Game.getScene().lastID++;
        ID = Game.getScene().lastID;
        this.x = x;
        this.y = y;
        text = string;
        scene.getLayers().get(0).getTexts().add(this);
    }

    public Text(String string, int x, int y, Layer lay) {
        Game.getScene().lastID++;
        ID = Game.getScene().lastID;
        this.x = x;
        this.y = y;
        text = string;
        lay.getTexts().add(this);
    }

    public Text(String string, int x, int y, Scene scene, Layer lay) {
        Game.getScene().lastID++;
        ID = Game.getScene().lastID;
        this.x = x;
        this.y = y;
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

    public String getText() { return text; }
    public void setText(String str) { text = str; }

    public int getSize() { return size; }
    public void setSize(int i) { size = (short) i; }

    public String getFont() { return font; }
    public void setFont(String str) { font = str; }

    public Color getColor() { return color; }
    public void setColor(Color col) { color = col; }

    public int getStyle() { return style; }
    public void setStyle(int styl) { style = (byte) styl; }

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

    public void setLayer(Layer l) {
        getLayer().getTexts().remove(this);
        l.getTexts().add(this);
    }

    public Layer getLayer() {
        for (Layer l : Game.getScene().getLayers())
            if (l.getTexts().contains(this))
                return l;
        return null;
    }

    public Layer getLayer(Scene scene) {
        for (Layer l : scene.getLayers())
            if (l.getTexts().contains(this))
                return l;
        return null;
    }

    public int getZ() { return getLayer().getTexts().indexOf(this); }
    public int getZ(Scene scene) { return getLayer(scene).getTexts().indexOf(this); }

    public void setZ(int index, Scene scene) {
        Layer l = getLayer(scene);
        int bf = l.getTexts().indexOf(this);
        l.getTexts().add(index, this);
        l.getTexts().remove(bf);
    }

    public void setZ(int index) {
        Layer l = getLayer();
        int bf = l.getTexts().indexOf(this);
        l.getTexts().add(index, this);
        l.getTexts().remove(bf);
    }

    public boolean isOffsetable() { return offsetable; }
    public void setOffsetable(boolean i) { offsetable = i; }

    public int getID() { return ID; }
}

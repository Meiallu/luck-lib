package org.luck.type;

import org.luck.system.Game;
import org.luck.system.Layer;
import org.luck.system.Scene;
import org.luck.util.Vector2D;

import java.awt.*;

@SuppressWarnings("unused")
public class Text {

    private final int ID;
    private double x;
    private double y;
    private float opacity = 1.0f;
    private boolean visible = true;
    private Color color = Color.BLACK;
    private byte style = Font.PLAIN;
    private String font = "SansSerif";
    private short size = 12;
    private String text;

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

    public Vector2D getLocation() { return new Vector2D(x, y); }

    public double getX() { return x; }

    public Text setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() { return y; }

    public Text setY(double y) {
        this.y = y;
        return this;
    }

    public Text setLocation(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public float getOpacity() { return opacity; }

    public Text setOpacity(float i) {
        opacity = i;
        return this;
    }

    public boolean isVisible() { return visible; }

    public Text setVisible(boolean i) {
        visible = i;
        return this;
    }

    public String getText() { return text; }

    public Text setText(String str) {
        text = str;
        return this;
    }

    public int getSize() { return size; }

    public Text setSize(int i) {
        size = (short) i;
        return this;
    }

    public String getFont() { return font; }

    public Text setFont(String str) {
        font = str;
        return this;
    }

    public Color getColor() { return color; }

    public Text setColor(Color col) {
        color = col;
        return this;
    }

    public int getStyle() { return style; }

    public Text setStyle(int styl) {
        style = (byte) styl;
        return this;
    }

    public Text setLayer(Layer l, int index) {
        getLayer().getTexts().remove(this);
        l.getTexts().add(index, this);
        return this;
    }

    public Text setLayer(Layer l, Scene scene) {
        getLayer(scene).getTexts().remove(this);
        l.getTexts().add(this);
        return this;
    }

    public Text setLayer(Layer l, int index, Scene scene) {
        getLayer(scene).getTexts().remove(this);
        l.getTexts().add(index, this);
        return this;
    }

    public Layer getLayer() {
        for (Layer l : Game.getScene().getLayers())
            if (l.getTexts().contains(this))
                return l;
        return null;
    }

    public Text setLayer(Layer l) {
        getLayer().getTexts().remove(this);
        l.getTexts().add(this);
        return this;
    }

    public Layer getLayer(Scene scene) {
        for (Layer l : scene.getLayers())
            if (l.getTexts().contains(this))
                return l;
        return null;
    }

    public int getZ() { return getLayer().getTexts().indexOf(this); }

    public Text setZ(int index) {
        Layer l = getLayer();
        int bf = l.getTexts().indexOf(this);
        l.getTexts().add(index, this);
        l.getTexts().remove(bf);
        return this;
    }

    public int getZ(Scene scene) { return getLayer(scene).getTexts().indexOf(this); }

    public Text setZ(int index, Scene scene) {
        Layer l = getLayer(scene);
        int bf = l.getTexts().indexOf(this);
        l.getTexts().add(index, this);
        l.getTexts().remove(bf);
        return this;
    }

    public int getID() { return ID; }
}

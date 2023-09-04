package org.luck.type;

import org.luck.system.Game;
import org.luck.system.Layer;
import org.luck.system.Scene;
import org.luck.util.Util;
import org.luck.util.Vector2D;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Object {
    private double x;
    private double y;
    private Sprite image;
    private ArrayList<Object> childs = new ArrayList<>();
    private Object father;
    private int ID;

    private byte frame = 0;
    private byte speed;
    private float scale = 1.0f;
    private short width;
    private short height;
    private float opacity = 1.0f;
    private float angle = 0.0f;

    private boolean offsetable = true;
    private boolean visible = true;

    private Vector2D origin = new Vector2D(0, 0);
    private ArrayList<Vector2D> points = new ArrayList<>();

    public Object(Sprite sprite) {
        image = sprite;
        speed = sprite.getSpeed();
        width = (short) sprite.getFrame(0).getWidth(null);
        height = (short) sprite.getFrame(0).getHeight(null);
    }

    public Object create(double x, double y) {
        Game.getScene().lastID++;
        Object obj = new Object(image);
        obj.ID = Game.getScene().lastID;
        setup(obj, x, y);
        Game.getScene().getLayers().get(0).getObjects().add(obj);
        return obj;
    }

    public Object create(double x, double y, Scene cena) {
        cena.lastID++;
        Object obj = new Object(image);
        obj.ID = cena.lastID;
        setup(obj, x, y);
        cena.getLayers().get(0).getObjects().add(obj);
        return obj;
    }

    public Object create(double x, double y, Layer lay) {
        Game.getScene().lastID++;
        Object obj = new Object(image);
        obj.ID = Game.getScene().lastID;
        setup(obj, x, y);
        lay.getObjects().add(obj);
        return obj;
    }

    public Object create(double x, double y, Scene cena, Layer lay) {
        cena.lastID++;
        Object obj = new Object(image);
        obj.ID = cena.lastID;
        setup(obj, x, y);
        lay.getObjects().add(obj);
        return obj;
    }

    public void destroy() {
        for (Object o : childs)
            o.getLayer().getObjects().remove(o);
        getLayer().getObjects().remove(this);
        childs = new ArrayList<>();
    }

    private void setup(Object o, double x, double y) {
        o.x = x; o.y = y;
        o.frame = frame;
        o.speed = speed;
        o.scale = scale;
        o.width = width;
        o.height = height;
        o.opacity = opacity;
        o.angle = angle;
        o.visible = visible;
        o.offsetable = offsetable;
        o.origin = origin;
        o.points = points;
        o.father = this;
        childs.add(o);
    }

    public Sprite getSprite() { return image; }
    public void setSprite(Sprite i) {
        image = i;
        if (father == null)
            for (Object o : childs)
                o.image = i;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double i) {
        x = i;
        if (father == null)
            for (Object o : childs)
                o.x = i;
    }

    public void setY(double i) {
        y = i;
        if (father == null)
            for (Object o : childs)
                o.y = i;
    }

    public ArrayList<Object> getChilds() { return childs; }
    public Object getFather() { return father; }

    public int getFrame() { return frame; }
    public void setFrame(int i) {
        frame = (byte) i;
        if (father == null)
            for (Object o : childs)
                o.frame = (byte) i;
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int fps) {
        speed = (byte) fps;
        if (father == null)
            for (Object o : childs)
                o.speed = (byte) fps;
    }

    public float getScale() { return scale; }
    public void setScale(float i) {
        scale = i;
        if (father == null)
            for (Object o : childs)
                o.scale = i;
    }

    public int getWidth() { return width; }
    public void setWidth(int i) {
        width = (short) i;
        if (father == null)
            for (Object o : childs)
                o.width = (short) i;
    }

    public int getHeight() { return height; }
    public void setHeight(int i) {
        height = (short) i;
        if (father == null)
            for (Object o : childs)
                o.height = (short) i;
    }

    public float getOpacity() { return opacity; }
    public void setOpacity(float i) {
        opacity = i;
        if (father == null)
            for (Object o : childs)
                o.opacity = i;
    }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean i) {
        visible = i;
        if (father == null)
            for (Object o : childs)
                o.visible = i;
    }

    public boolean isOffsetable() { return offsetable; }
    public void setOffsetable(boolean i) {
        offsetable = i;
        if (father == null)
            for (Object o : childs)
                o.offsetable = i;
    }

    public float getAngle() { return angle; }
    public void setAngle(float i) {
        angle = i;
        if (father == null)
            for (Object o : childs)
                o.angle = i;
    }

    public void setLayer(Layer l, int index) {
        if (father == null)
            for (Object o : childs) {
                o.getLayer().getObjects().remove(o);
                l.getObjects().add(index, o);
            }
        else {
            getLayer().getObjects().remove(this);
            l.getObjects().add(index, this);
        }
    }

    public void setLayer(Layer l, Scene scene) {
        if (father == null)
            for (Object o : childs) {
                o.getLayer(scene).getObjects().remove(o);
                l.getObjects().add(o);
            }
        else {
            getLayer(scene).getObjects().remove(this);
            l.getObjects().add(this);
        }
    }

    public void setLayer(Layer l, int index, Scene scene) {
        if (father == null)
            for (Object o : childs) {
                o.getLayer(scene).getObjects().remove(o);
                l.getObjects().add(index, o);
            }
        else {
            getLayer(scene).getObjects().remove(this);
            l.getObjects().add(index, this);
        }
    }

    public Layer getLayer() {
        for (Layer l : Game.getScene().getLayers()) {
            if (l.getObjects().contains(this)) {
                return l;
            }
        }
        return Game.getScene().getLayers().get(0);
    }

    public void setLayer(Layer l) {
        if (father == null)
            for (Object o : childs) {
                o.getLayer().getObjects().remove(o);
                l.getObjects().add(o);
            }
        else {
            getLayer().getObjects().remove(this);
            l.getObjects().add(this);
        }
    }

    public Layer getLayer(Scene scene) {
        for (Layer l : scene.getLayers())
            if (l.getObjects().contains(this)) {
                return l;
            }
        return Game.getScene().getLayers().get(0);
    }

    public int getZ() { return getLayer().getObjects().indexOf(this); }
    public int getZ(Scene scene) { return getLayer(scene).getObjects().indexOf(this); }

    public void setZ(int index, Scene scene) {
        if (father == null) {
            for (Object o : childs) {
                Layer l = o.getLayer(scene);
                int bf = l.getObjects().indexOf(o);
                l.getObjects().add(index, o);
                l.getObjects().remove(bf);
            }
        } else {
            Layer l = getLayer(scene);
            int bf = l.getObjects().indexOf(this);
            l.getObjects().add(index, this);
            l.getObjects().remove(bf);
        }
    }

    public void setZ(int index) {
        if (father == null) {
            for (Object o : childs) {
                Layer l = o.getLayer();
                int bf = l.getObjects().indexOf(o);
                l.getObjects().add(index, o);
                l.getObjects().remove(bf);
            }
        } else {
            Layer l = getLayer();
            int bf = l.getObjects().indexOf(this);
            l.getObjects().add(index, this);
            l.getObjects().remove(bf);
        }
    }

    public boolean isMirrored() { return width < 0; }
    public void setMirrored(boolean i) {
        if (i) {
            if (width > 0) {
                width = (short) -width;
            }
        } else {
            if (width < 0) {
                width = (short) Math.abs(width);
            }
        }
    }

    public boolean isFlipped() { return height < 0; }
    public void setFlipped(boolean i) {
        if (i) {
            if (height > 0) {
                height = (short) -height;
            }
        } else {
            if (height < 0) {
                height = (short) Math.abs(height);
            }
        }
    }

    public boolean isPlaceMeeting(double xDif, double yDif, Object obj) {
        if (obj.father == null) {
            for (Object o : obj.childs) {
                boolean xx = false; boolean yy = false;
                if (Util.isBetween(getX() + xDif, o.getX(), o.getX() + o.width) ||
                        Util.isBetween(getX() + width + xDif, o.getX(), o.getX() + o.width) ||
                        Util.isBetween(o.getX(), getX() + xDif, getX() + width + xDif) ||
                        Util.isBetween(o.getX() + o.width, getX() + xDif, getX() + width + xDif)) {
                    xx = true;
                }
                if (Util.isBetween(getY() + yDif, o.getY(), o.getY() + o.height) ||
                        Util.isBetween(getY() + height + yDif, o.getY(), o.getY() + o.height) ||
                        Util.isBetween(o.getY(), getY() + yDif, getY() + height + yDif) ||
                        Util.isBetween(o.getY() + obj.height, getY() + yDif, getY() + height + yDif)) {
                    yy = true;
                }
                if (xx && yy) return true;
            }
            return false;
        } else {
            boolean xx = false; boolean yy = false;
            if (Util.isBetween(getX() + xDif, obj.getX(), obj.getX() + obj.width) ||
                    Util.isBetween(getX() + width + xDif, obj.getX(), obj.getX() + obj.width) ||
                    Util.isBetween(obj.getX(), getX() + xDif, getX() + width + xDif) ||
                    Util.isBetween(obj.getX() + obj.width, getX() + xDif, getX() + width + xDif)) {
                xx = true;
            }
            if (Util.isBetween(getY() + yDif, obj.getY(), obj.getY() + obj.height) ||
                    Util.isBetween(getY() + height + yDif, obj.getY(), obj.getY() + obj.height) ||
                    Util.isBetween(obj.getY(), getY() + yDif, getY() + height + yDif) ||
                    Util.isBetween(obj.getY() + obj.height, getY() + yDif, getY() + height + yDif)) {
                yy = true;
            }
            return xx && yy;
        }
    }

    public Vector2D getOrigin() { return origin; }
    public void setOrigin(Vector2D vec) {
        origin = vec;
        if (father == null)
            for (Object o : childs)
                o.origin = vec;
    }

    public void setOrigin(int x, int y) {
        origin.setX(x);
        origin.setY(y);
        if (father == null)
            for (Object o : childs) {
                o.origin.setX(x);
                o.origin.setY(y);
            }
    }

    public Vector2D addPoint(double x, double y) {
        Vector2D point = new Vector2D(x, y);
        points.add(point);
        if (father == null) {
            for (Object o : childs) {
                point = new Vector2D(x, y);
                o.points.add(point);
            }
        } else {
            return point;
        }
        return null;
    }

    public void removePoint(int index) {
        points.remove(index);
        if (father == null)
            for (Object o : childs)
                o.points.remove(index);
    }

    public void removePoint(Vector2D point) {
        points.remove(point);
        if (father == null)
            for (Object o : childs)
                o.points.remove(point);
    }

    public Vector2D getPoint(int index) { return points.get(index); }
    public ArrayList<Vector2D> getPoints() { return points; }

    public double getPointX(int index) { return x + points.get(index).getX(); }
    public double getPointY(int index) { return y + points.get(index).getY(); }

    public int getID() { return ID; }
}
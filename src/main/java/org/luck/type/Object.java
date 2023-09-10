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

    private float x_scale = 1.0f;
    private float y_scale = 1.0f;

    private float width;
    private float height;
    private float opacity = 1.0f;
    private float angle = 0.0f;

    private boolean visible = true;

    private Vector2D origin = new Vector2D(0, 0);
    private ArrayList<Vector2D> points = new ArrayList<>();

    public Object(Sprite sprite) {
        image = sprite;
        speed = sprite.getSpeed();
        width = sprite.getFrame(0).getWidth(null);
        height = sprite.getFrame(0).getHeight(null);
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
        o.setX(x);
        o.setY(y);
        o.frame = frame;
        o.speed = speed;
        o.x_scale = x_scale;
        o.y_scale = y_scale;
        o.width = width;
        o.height = height;
        o.opacity = opacity;
        o.angle = angle;
        o.visible = visible;
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

    public double getAbX() { return x; }
    public double getAbY() { return y; }

    public double getX() {
        float dif = width / image.getFrame(0).getWidth(null);
        double org = origin.getX() * x_scale;
        return x + (org * dif);
    }

    public double getY() {
        float dif = height / image.getFrame(0).getHeight(null);
        double org = origin.getY() * y_scale;
        return y + (org * dif);
    }

    public void setX(double i) {
        float dif = width / image.getFrame(0).getWidth(null);
        double org = origin.getX() * x_scale;
        x = i - (org * dif);
        if (father == null)
            for (Object o : childs) {
                dif = o.width / o.image.getFrame(0).getWidth(null);
                org = o.origin.getX() * o.x_scale;
                o.x = i - (org * dif);
            }
    }

    public void setY(double i) {
        float dif = height / image.getFrame(0).getHeight(null);
        double org = origin.getY() * y_scale;
        y = i - (org * dif);
        if (father == null)
            for (Object o : childs) {
                dif = o.height / o.image.getFrame(0).getHeight(null);
                org = o.origin.getY() * o.y_scale;
                o.y = i - (org * dif);
            }
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

    public float getScaleX() { return x_scale; }
    public void setScaleX(float i) {
        double div = origin.getX() / image.getFrame(0).getWidth(null);
        float dif = (float) (((i * width) - (width * x_scale)) * div);
        x_scale = i; x -= dif;
        if (father == null)
            for (Object o : childs) {
                div = o.origin.getX() / o.image.getFrame(0).getWidth(null);
                dif = (float) (((i * o.width) - (o.width * o.x_scale)) * div);
                o.x_scale = i; o.x -= dif;
            }
    }

    public float getScaleY() { return y_scale; }
    public void setScaleY(float i) {
        double div = origin.getY() / image.getFrame(0).getHeight(null);
        float dif = (float) (((i * height) - (height * y_scale)) * div);
        y_scale = i; y -= dif;
        if (father == null)
            for (Object o : childs) {
                div = o.origin.getY() / o.image.getFrame(0).getHeight(null);
                dif = (float) (((i * o.height) - (o.height * o.y_scale)) * div);
                o.y_scale = i; o.y -= dif;
            }
    }

    public float getAbWidth() { return width * x_scale; }
    public float getAbHeight() { return height * y_scale; }

    public float getWidth() { return width; }
    public float getHeight() { return height; }

    public void setWidth(float i) {
        double div = origin.getX() / image.getFrame(0).getWidth(null);
        float dif = (float) ((i - width) * div);
        width = i; x -= dif;
        if (father == null)
            for (Object o : childs) {
                div = o.origin.getX() / o.image.getFrame(0).getWidth(null);
                dif = (float) ((i - o.width) * div);
                o.width = i; o.x -= dif;
            }
    }

    public void setHeight(float i) {
        double div = origin.getY() / image.getFrame(0).getHeight(null);
        float dif = (float) ((i - height) * div);
        height = i; y -= dif;
        if (father == null)
            for (Object o : childs) {
                div = o.origin.getY() / o.image.getFrame(0).getHeight(null);
                dif = (float) ((i - o.height) * div);
                o.height = i; o.y -= dif;
            }
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

    public boolean isPlaceMeeting(double xDif, double yDif, Object obj) {
        if (obj.father == null) {
            for (Object o : obj.childs) {
                boolean xx = false; boolean yy = false;
                if (Util.isBetween(getAbX() + xDif, o.getAbX(), o.getAbX() + o.getAbWidth()) ||
                        Util.isBetween(getAbX() + getAbWidth() + xDif, o.getAbX(), o.getAbX() + o.getAbWidth()) ||
                        Util.isBetween(o.getAbX(), getAbX() + xDif, getAbX() + getAbWidth() + xDif) ||
                        Util.isBetween(o.getAbX() + o.getAbWidth(), getAbX() + xDif, getAbX() + getAbWidth() + xDif)) {
                    xx = true;
                }
                if (Util.isBetween(getAbY() + yDif, o.getAbY(), o.getAbY() + o.getAbHeight()) ||
                        Util.isBetween(getAbY() + getAbHeight() + yDif, o.getAbY(), o.getAbY() + o.getAbHeight()) ||
                        Util.isBetween(o.getAbY(), getAbY() + yDif, getAbY() + getAbHeight() + yDif) ||
                        Util.isBetween(o.getAbY() + obj.getAbHeight(), getAbY() + yDif, getAbY() + getAbHeight() + yDif)) {
                    yy = true;
                }
                if (xx && yy) return true;
            }
            return false;
        } else {
            boolean xx = false; boolean yy = false;
            if (Util.isBetween(getAbX() + xDif, obj.getAbX(), obj.getAbX() + obj.getAbWidth()) ||
                    Util.isBetween(getAbX() + getAbWidth() + xDif, obj.getAbX(), obj.getAbX() + obj.getAbWidth()) ||
                    Util.isBetween(obj.getAbX(), getAbX() + xDif, getAbX() + getAbWidth() + xDif) ||
                    Util.isBetween(obj.getAbX() + obj.getAbWidth(), getAbX() + xDif, getAbX() + getAbWidth() + xDif)) {
                xx = true;
            }
            if (Util.isBetween(getAbY() + yDif, obj.getAbY(), obj.getAbY() + obj.getAbHeight()) ||
                    Util.isBetween(getAbY() + getAbHeight() + yDif, obj.getAbY(), obj.getAbY() + obj.getAbHeight()) ||
                    Util.isBetween(obj.getAbY(), getAbY() + yDif, getAbY() + getAbHeight() + yDif) ||
                    Util.isBetween(obj.getAbY() + obj.getAbHeight(), getAbY() + yDif, getAbY() + getAbHeight() + yDif)) {
                yy = true;
            }
            return xx && yy;
        }
    }

    public Vector2D getOrigin() { return origin; }
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

    public double getPointX(int index) {
        float dif = width / image.getFrame(0).getWidth(null);
        double org = points.get(index).getX() * x_scale;
        return x + (org * dif);
    }

    public double getPointY(int index) {
        float dif = height / image.getFrame(0).getHeight(null);
        double org = points.get(index).getY() * y_scale;
        return y + (org * dif);
    }

    public int getID() { return ID; }
}
package org.luck.system.type;

import java.util.ArrayList;

import org.luck.system.nes.Game;
import org.luck.system.nes.Layer;
import org.luck.system.nes.Scene;
import org.luck.util.Util;
import org.luck.util.Vector2D;

public class Object {
    private float x;
    private float y;
    private Sprite image;
    private ArrayList<Object> childs = new ArrayList<>();
    private Object father;
    private int ID;

    private Animation anim;
    private byte frame = 0;
    private byte speed = 5;
    private float scale = 1.0f;
    private short width = 32;
    private short height = 32;
    private float opacity = 1.0f;
    private float angle = 0.0f;

    private boolean offsetable = true;
    private boolean mirrored = false;
    private boolean flipped = false;
    private boolean visible = true;

    private Vector2D origin = new Vector2D(0, 0);
    private ArrayList<Vector2D> points = new ArrayList<>();

    public Object(Sprite sprite) {
        image = sprite;
        anim = sprite.getDefault();
        speed = anim.getSpeed();
        width = (short) anim.getFrame(0).getWidth(null);
        height = (short) anim.getFrame(0).getHeight(null);
    }

    public Object create(float x, float y) {
        Game.getScene().lastID++;
        Object obj = new Object(image);
        obj.ID = Game.getScene().lastID;
        setup(obj, x, y);
        Game.getScene().getLayers().get(0).getObjects().add(obj);
        return obj;
    }

    public Object create(float x, float y, Scene cena) {
        cena.lastID++;
        Object obj = new Object(image);
        obj.ID = cena.lastID;
        setup(obj, x, y);
        cena.getLayers().get(0).getObjects().add(obj);
        return obj;
    }

    public Object create(float x, float y, Layer lay) {
        Game.getScene().lastID++;
        Object obj = new Object(image);
        obj.ID = Game.getScene().lastID;
        setup(obj, x, y);
        lay.getObjects().add(obj);
        return obj;
    }

    public Object create(float x, float y, Scene cena, Layer lay) {
        cena.lastID++;
        Object obj = new Object(image);
        obj.ID = cena.lastID;
        setup(obj, x, y);
        lay.getObjects().add(obj);
        return obj;
    }

    private void setup(Object o, float x, float y) {
        o.x = x; o.y = y;
        o.frame = frame; 
        o.speed = speed;
        o.scale = scale;
        o.width = width; o.height = height;
        o.opacity = opacity;
        o.angle = angle;
        o.visible = visible;
        o.offsetable = offsetable;
        o.mirrored = mirrored; o.flipped = flipped;
        o.origin = origin; o.points = points;
        o.father = this;
        childs.add(o);
    }

    public Sprite getSprite() { return image; }
    public void setSprite(Sprite i) {
        image = i; 
        if (father == null)
            for ( Object o : childs )
                o.setSprite(i);
    }

    public float getAbX() { return x + origin.getX(); }
    public float getX() { return x; }
    public void setX(float i) {
        x = (float) i; 
        if (father == null)
            for ( Object o : childs )
                o.setX(i);
    }

    public float getAbY() { return y + origin.getY(); }
    public float getY() { return y; }
    public void setY(double i) { 
        y = (float) i; 
        if (father == null)
            for ( Object o : childs )
                o.setY(i);
    }

    public ArrayList<Object> getChilds() { return childs; }
    public Object getFather() { return father; }

    public Animation getAnimation() {return anim; }
    public void setAnimation(Animation i) {
        if (anim != i) {
            anim = i;
            width = (short) i.getFrame(0).getWidth(null);
            height = (short) i.getFrame(0).getHeight(null);
            speed = i.getSpeed();
        }
        if (father == null) {
            for (Object o : childs) {
                if (o.getAnimation() != i) {
                    o.setAnimation(i);
                    o.setWidth( i.getFrame(0).getWidth(null) );
                    o.setHeight( i.getFrame(0).getHeight(null) );
                    o.setSpeed( i.getSpeed() );
                }
            }
        }
    }

    public int getFrame() { return frame; }
    public void setFrame(int i) { 
        frame = (byte) i; 
        if (father == null)
            for ( Object o : childs )
                o.setFrame(i);
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int fps) { 
        if (fps != speed)
            speed = (byte) fps;
        if (father == null) {
            for ( Object o : childs ) {
                o.setSpeed(fps);
            }
        }
     }

    public float getScale() { return scale; }
    public void setScale(float i) { 
        scale = i; 
        if (father == null)
            for ( Object o : childs )
                o.setScale(i);
    }

    public int getWidth() { return width; }
    public void setWidth(int i) { 
        width = (short) i; 
        if (father == null)
            for ( Object o : childs )
                o.setHeight(i);
    }

    public int getHeight() { return height; }
    public void setHeight(int i) { 
        height = (short) i; 
        if (father == null)
            for ( Object o : childs )
                o.setHeight(i);
    }

    public float getOpacity() { return opacity; }
    public void setOpacity(float i) {
        opacity = i; 
        if (father == null)
            for ( Object o : childs )
                o.setOpacity(i);
    }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean i) {
        visible = i; 
        if (father == null)
            for ( Object o : childs )
                o.setVisible(i);
    }

    public boolean isOffsetable() { return offsetable; }
    public void setOffsetable(boolean i) {
        offsetable = i; 
        if (father == null)
            for ( Object o : childs )
                o.setOffsetable(i);
    }

    public float getAngle() { return angle; }
    public void setAngle(float i) {
        angle = i; 
        if (father == null)
            for ( Object o : childs )
                o.setAngle(i);
    }

    public void setLayer(Layer l) {
        getLayer().getObjects().remove(this);
        l.getObjects().add(this);
    }

    public void setLayer(Layer l, int index) {
        getLayer().getObjects().remove(this);
        l.getObjects().add(index, this);
    }

    public void setLayer(Layer l, Scene scene) {
        getLayer(scene).getObjects().remove(this);
        l.getObjects().add(this);
    }

    public void setLayer(Layer l, int index, Scene scene) {
        getLayer(scene).getObjects().remove(this);
        l.getObjects().add(index, this);
    }

    public Layer getLayer() {
        for ( Layer l : Game.getScene().getLayers() ) {
            if ( l.getObjects().contains(this) ) {
                return l;
            }
        }
        return Game.getScene().getLayers().get(0);            
    }

    public Layer getLayer(Scene scene) {
        for ( Layer l : scene.getLayers() )
            if ( l.getObjects().contains(this) ) {
                return l;
            }
        return Game.getScene().getLayers().get(0);            
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

    public int getZ() { return getLayer().getObjects().indexOf(this); }
    public int getZ(Scene scene) { return getLayer(scene).getObjects().indexOf(this); }

    public boolean isMirrored() { return mirrored; }
    public boolean isFlipped() { return flipped; }

    public void setMirrored(boolean i) { mirrored = i; }
    public void setFlipped(boolean i) { flipped = i; }

    public boolean isPlaceMeeting(double xDif, double yDif, Object obj) {
        if ( obj.father == null ) {
            for (Object o : obj.childs) {
                boolean xx = false; boolean yy = false;
                if (Util.isBetween(getAbX() + xDif, o.getAbX(), o.getAbX() + o.width) || 
                    Util.isBetween(getAbX() + width + xDif, o.getAbX(), o.getAbX() + o.width ) ||
                    Util.isBetween(o.getAbX(), getAbX() + xDif, getAbX() + width + xDif) ||
                    Util.isBetween(o.getAbX() + o.width, getAbX() + xDif, getAbX() + width + xDif)) {
                        xx = true;
                }
                if (Util.isBetween(getAbY() + yDif, o.getAbY(), o.getAbY() + o.height) || 
                    Util.isBetween(getAbY() + height + yDif, o.getAbY(), o.getAbY() + o.height) ||
                    Util.isBetween(o.getAbY(), getAbY() + yDif, getAbY() + height + yDif) ||
                    Util.isBetween(o.getAbY() + obj.height, getAbY() + yDif, getAbY() + height + yDif)) {
                        yy = true;
                }
                if (xx && yy) { return true; }
            }
        } else {
            boolean xx = false; boolean yy = false;
            if (Util.isBetween(getAbX() + xDif, obj.getAbX(), obj.getAbX() + obj.width) || 
                Util.isBetween(getAbX() + width + xDif, obj.getAbX(), obj.getAbX() + obj.width) ||
                Util.isBetween(obj.getAbX(), getAbX() + xDif, getAbX() + width + xDif) ||
                Util.isBetween(obj.getAbX() + obj.width, getAbX() + xDif, getAbX() + width + xDif)) {
                    xx = true;
            }
            if (Util.isBetween(getAbY() + yDif, obj.getAbY(), obj.getAbY() + obj.height) || 
                Util.isBetween(getAbY() + height + yDif, obj.getAbY(), obj.getAbY() + obj.height) ||
                Util.isBetween(obj.getAbY(), getAbY() + yDif, getAbY() + height + yDif) ||
                Util.isBetween(obj.getAbY() + obj.height, getAbY() + yDif, getAbY() + height + yDif)) {
                    yy = true;
            }
            if (xx && yy) { return true; } else { return false; }
        }
        return false;
    }

    public void destroy() {
        for (Object o : childs) 
            o.getLayer().getObjects().remove(o);
        getLayer().getObjects().remove(this);
        childs = new ArrayList<>();
    }

    public void setOrigin(Vector2D vec) { origin = vec; }
    public void setOrigin(int x, int y) { origin.setX(x); origin.setY(y); }
    public Vector2D getOrigin() { return origin; }

    public Vector2D addPoint(float x, float y) { 
        Vector2D point = new Vector2D(x, y);
        points.add(point);
        return point;
    }

    public void removePoint(int index) { points.remove(index); }
    public void removePoint(Vector2D point) { points.remove(point); }

    public Vector2D getPoint(int index) { return points.get(index); }
    public ArrayList<Vector2D> getPoints() { return points; }

    public float getPointX(int index) { return x + points.get(index).getX(); }
    public float getPointY(int index) { return y + points.get(index).getY(); }

    public int getID() { return ID; }
}
package org.meiallu.system.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.meiallu.system.Game;
import org.meiallu.system.Layer;
import org.meiallu.system.Scene;

public class Object {

    private double x;
    private double y;
    private Sprite image;
    private List<Object> childs = new ArrayList<Object>();
    private Object father;
    private int ID;
    
    private boolean offsetable = true;

    private Animation anim;
    private int frame = 0;
    private float speed = 5.0f;
    private float scale = 1.0f;
    private int width = 32;
    private int height = 32;
    private float opacity = 1.0f;
    private boolean visible = true;
    // private float angle = 0.0f;

    private Timer t = new Timer();

    public void resetSpeed() {      
        t.scheduleAtFixedRate( new TimerTask() {
            @Override
            public void run() {
                if ( anim.getFrames().size() == frame + 1 ) {
                    frame = 0;
                } else {
                    frame++;
                }
            }
        }, 0, (int) (1000 / speed) );
    }

    public Timer getTimer() {
        return t;
    }

    public void setTimer(Timer timer) {
        t = timer;
    }

    public Object(Sprite sprite) {
        image = sprite;
        anim = sprite.getDefault();
        speed = anim.getSpeed();
        width = anim.getFrame(0).getWidth(null);
        height = anim.getFrame(0).getHeight(null);
        resetSpeed();
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

    private void setup(Object o, double x, double y) {
        o.x = x; 
        o.y = y;
        o.frame = frame; 
        o.speed = speed;
        o.scale = scale;
        o.width = width; 
        o.height = height;
        o.opacity = opacity; 
        o.visible = visible;
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

    public double getX() { return x; }
    public void setX(double i) {
        x = i; 
        if (father == null)
            for ( Object o : childs )
                o.setX(i);
    }

    public double getY() { return y; }
    public void setY(double i) { 
        y = i; 
        if (father == null)
            for ( Object o : childs )
                o.setY(i);
    }

    public List<Object> getChilds() { return childs; }
    public Object getFather() { return father; }

    public Animation getAnimation() {return anim; }
    public void setAnimation(Animation i) {
        anim = i;
        width = i.getFrame(0).getWidth(null);
        height = i.getFrame(0).getHeight(null);
        if ( i.getSpeed() != speed )
            speed = i.getSpeed();
            t.cancel();
            t = new Timer();
            resetSpeed();
        if (father == null) {
            for ( Object o : childs ) {
                o.setAnimation(i);
                o.setWidth( i.getFrame(0).getWidth(null) );
                o.setHeight( i.getFrame(0).getHeight(null) );
                if ( i.getSpeed() != o.getSpeed() )
                    o.setSpeed( i.getSpeed() );
                    o.getTimer().cancel();
                    o.setTimer( new Timer() );
                    o.resetSpeed();
            }
        }
    }

    public int getFrame() { return frame; }
    public void setFrame(int i) { 
        frame = i; 
        if (father == null)
            for ( Object o : childs )
                o.setFrame(i);
    }

    public float getSpeed() { return speed; }
    public void setSpeed(float i) { 
        if (i != speed)
            speed = i;
            t.cancel();
            t = new Timer();
            resetSpeed();
        if (father == null) {
            for ( Object o : childs ) {
                if ( i != o.getSpeed() )
                    o.setSpeed(i);
                    o.getTimer().cancel();
                    o.setTimer( new Timer() );
                    o.resetSpeed();
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
        width = i; 
        if (father == null)
            for ( Object o : childs )
                o.setHeight(i);
    }

    public void setSize(int w, int h) { 
        width = w;
        height = h;
        if (father == null)
            for ( Object o : childs )
                o.setSize(w, h);
    }

    public int getHeight() { return height; }
    public void setHeight(int i) { 
        height = i; 
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

    // public float getAngle() { return angle; }
    // public void setAngle(float i) { angle = i; }

    public int getID() { return ID; }
}
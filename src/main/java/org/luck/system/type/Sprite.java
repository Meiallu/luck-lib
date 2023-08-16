package org.luck.system.type;

import java.util.ArrayList;
import java.util.List;

public class Sprite {
    private Animation defAnim;
    private List<Animation> animList = new ArrayList<Animation>();

    public Sprite(Animation anim) {
        defAnim = anim;
        animList.add(anim);
    }

    public void addAnimation(Animation anim) {
        animList.add(anim);
    }

    public void removeAnimation(Animation anim) {
        animList.remove(anim);
    }

    public void removeAnimation(int index) {
        animList.remove(index);
    }

    public void setDefault(int index) {
        defAnim = animList.get(index);
    }

    public void setDefault(Animation anim) {
        defAnim = anim;
    }

    public Animation getDefault() {
        return defAnim;
    }
}
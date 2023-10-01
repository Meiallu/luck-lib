package org.luck.system;

import org.luck.type.Object;
import org.luck.type.Text;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Layer {

    private ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Text> texts = new ArrayList<>();
    private boolean visible = true;

    public Layer() {Game.getScene().getLayers().add(this);}

    public Layer(Scene scene) {scene.getLayers().add(this);}

    public int getIndex() {return Game.getScene().getLayers().indexOf(this);}

    public Layer setIndex(int index) {
        int idx = Game.getScene().getLayers().indexOf(this);
        Game.getScene().getLayers().add(index, this);
        Game.getScene().getLayers().remove(idx);
        return this;
    }

    public int getIndex(Scene cena) {return cena.getLayers().indexOf(this);}

    public boolean isVisible() {return visible;}

    public Layer setVisible(boolean bool) {
        visible = bool;
        return this;
    }

    public ArrayList<Object> getObjects() {return objects;}

    public ArrayList<Text> getTexts() {return texts;}
}

package org.luck.system.nes;

import java.util.ArrayList;
import java.util.List;

import org.luck.system.type.Object;
import org.luck.util.Text;

public class Layer {
    private List<Object> objects = new ArrayList<Object>();
    private List<Text> texts = new ArrayList<Text>();

    public Layer() {
        Game.getScene().getLayers().add(this);
    }

    public Layer(Scene scene) {
        scene.getLayers().add(this);
    }
     
    public List<Object> getObjects() { return objects; }
    public List<Text> getTexts() { return texts; }
}

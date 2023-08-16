package org.luck.system.nes;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private String name;
    private List<Layer> layers = new ArrayList<Layer>();
    public int lastID = 0; 

    public Scene(String name) {
        this.name = name;
        new Layer(this);
    }

    public List<Layer> getLayers() { return layers; }

    public String getName() { return name; }
    public void setName(String str) { name = str; }
}

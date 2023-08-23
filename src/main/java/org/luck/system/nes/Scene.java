package org.luck.system.nes;

import java.util.ArrayList;

public class Scene {
    private String name;
    private ArrayList<Layer> layers = new ArrayList<Layer>();
    public int lastID = 0; 

    public Scene(String name) {
        this.name = name;
        new Layer(this);
    }

    public ArrayList<Layer> getLayers() { return layers; }

    public String getName() { return name; }
    public void setName(String str) { name = str; }
}

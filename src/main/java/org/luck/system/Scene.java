package org.luck.system;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Scene {

    public int lastID = 0;
    private String name;
    private ArrayList<Layer> layers = new ArrayList<>();

    public Scene(String name) {
        this.name = name;
        new Layer(this);
    }

    public ArrayList<Layer> getLayers() {return layers;}

    public String getName() {return name;}

    public Scene setName(String str) {
        name = str;
        return this;
    }
}

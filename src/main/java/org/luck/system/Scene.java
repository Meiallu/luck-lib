package org.luck.system;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Scene {

    public int lastID = 0;
    private String name;
    private List<Layer> layers = new ArrayList<>();

    public Scene(String name) {
        this.name = name;
        new Layer(this);
    }

    public List<Layer> getLayers() {return layers;}

    public String getName() {return name;}

    public Scene setName(String str) {
        name = str;
        return this;
    }
}

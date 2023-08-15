package org.meiallu.system;

import java.util.ArrayList;
import java.util.List;

import org.meiallu.system.type.Object;
import org.meiallu.util.Text;

public class Scene {
    private String name;
    private List<Object> objects = new ArrayList<Object>();
    private List<Text> texts = new ArrayList<Text>();
    public int lastID = 0; 

    public Scene(String name) {
        this.name = name;
    }
     
    public List<Object> getObjects() { return objects; }
    public List<Text> getTexts() { return texts; }

    public String getName() { return name; }
    public void setName(String str) { name = str; }
}

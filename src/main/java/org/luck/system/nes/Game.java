package org.luck.system.nes;

import java.util.ArrayList;

public class Game {
    private static Scene cena = new Scene("room");
    private static ArrayList<Scene> cenas = new ArrayList<Scene>();

    public static Scene getScene() { return cena; }
    public static ArrayList<Scene> getScenes() { return cenas; }

    public static void setScene(Scene scene) {
        cena = scene;
        if ( !cenas.contains(scene) ) {
            cenas.add(scene);
        }
    }

    public static Scene createScene(String name) {
        Scene cena = new Scene(name);
        cenas.add(cena);
        return cena;
    }

    public static void removeScene(String name) {
        for ( Scene cena : cenas ) {
            if ( cena.getName() == name ) {
                cenas.remove(cena);
            }
        }
    }

    public static void removeScene(Scene scene) {
        cenas.remove(scene);
    }

    public static void removeScene(int index) {
        cenas.remove(index);
    }
}
package org.luck.system;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Game {
    private static Scene cena = new Scene("room");
    private static ArrayList<Scene> cenas = new ArrayList<>();

    public static Scene getScene() { return cena; }
    public static ArrayList<Scene> getScenes() { return cenas; }

    public static void setScene(Scene scene) {
        cena = scene;
        if (!cenas.contains(scene))
            cenas.add(scene);
    }

    public static Scene createScene(String name) {
        Scene cena = new Scene(name);
        cenas.add(cena);
        return cena;
    }

    public static void removeScene(String name) { cenas.removeIf(cena -> cena.getName().equals(name)); }
    public static void removeScene(Scene scene) { cenas.remove(scene); }
    public static void removeScene(int index) { cenas.remove(index); }
}
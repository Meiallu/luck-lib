package org.luck.util;

import org.luck.system.Instance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Keyboard implements KeyListener {

    private static List<Character> keys = new ArrayList<>();
    private static char last = 0;
    private static char pressed = 0;

    private JFrame window = Instance.getWindow();
    private GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    public Keyboard() {
        Instance.getWindow().addKeyListener(this);
    }

    public static boolean isPressingAnyKey() {
        return (keys.size() > 0);
    }

    public static boolean isPressed(char key) {
        return keys.contains(key);
    }

    public static boolean onPressAnyKey() {
        boolean i = (pressed != 0);
        if (i)
            pressed = 0;
        return i;
    }

    public static boolean onPress(char key) {
        boolean i = (pressed == key);
        if (pressed != 0)
            last = pressed;
        if (i)
            pressed = 0;
        return i;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 122)
            if (dev.getFullScreenWindow() == null) {
                window.dispose();
                window.setResizable(false);
                window.setUndecorated(true);
                dev.setFullScreenWindow(window);
            } else {
                window.dispose();
                window.setResizable(true);
                dev.setFullScreenWindow(null);
                window.setUndecorated(false);
                window.setVisible(true);
            }
        char c = Character.toLowerCase(e.getKeyChar());
        if (last != c)
            pressed = c;
        if (!keys.contains(c))
            keys.add(keys.size(), c);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = Character.toLowerCase(e.getKeyChar());
        if (last == c)
            last = 0;
        if (keys.contains(c))
            keys.remove(keys.indexOf(c));
    }
}

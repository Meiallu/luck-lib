package org.luck.util;

import org.luck.system.Instance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Input implements KeyListener, MouseListener {
    private static List<Character> keys = new ArrayList<>();
    private static char last = 0;
    private static char pressed = 0;

    private JFrame window = Instance.getWindow();
    private GraphicsDevice dev = GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .getScreenDevices()[0];

    public Input() {
        Instance.getWindow().addKeyListener(this);
        Instance.getWindow().addMouseListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

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
        char c = Character.toLowerCase( e.getKeyChar() );
        if (last != c) pressed = c;
        if ( !keys.contains(c) )
            keys.add(keys.size(), c);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = Character.toLowerCase( e.getKeyChar() );
        if (last == c) last = 0;
        if ( keys.contains(c) )
            keys.remove( keys.indexOf(c) );
    }

    public static boolean isPressingAnyKey() { return (keys.size() > 0); }
    public static boolean isPressed(char key) { return keys.contains(key); }

    public static boolean onPressAnyKey() {
        boolean i = (pressed != 0);
        pressed = 0;
        return i;
    }

    public static boolean onPress(char key) {
        boolean i = (pressed == key);
        if (pressed != 0) last = pressed;
        pressed = 0;
        return i;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    public static double getMouseX() {
        double pointer = MouseInfo.getPointerInfo().getLocation().getX();
        double offset = Instance.getCanvas().getOffset().getX();
        double win = Instance.getWindow().getX() + Instance.getWindow().getRootPane().getX();
        double scale = Instance.getCanvas().getScale();
        return (pointer - (win - offset)) / scale;
    }

    public static double getMouseY() {
        double pointer = MouseInfo.getPointerInfo().getLocation().getY();
        double offset = Instance.getCanvas().getOffset().getY();
        double win = Instance.getWindow().getY() + Instance.getWindow().getRootPane().getY();
        double scale = Instance.getCanvas().getScale();
        return (pointer - (win - offset)) / scale;
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

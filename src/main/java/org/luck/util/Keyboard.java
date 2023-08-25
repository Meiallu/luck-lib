package org.luck.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.luck.system.nes.Instance;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private static List<Character> keys = new ArrayList<Character>();
    private JFrame window = Instance.getWindow();
    private GraphicsDevice dev = GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .getScreenDevices()[0];

    public Keyboard() { Instance.getWindow().addKeyListener(this); }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 122)
            if (dev.getFullScreenWindow() == null)
                setFullScreen();
            else
                setNormal();
        char c = Character.toLowerCase( e.getKeyChar() );
        if ( !keys.contains(c) )
            keys.add(keys.size(), c);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = Character.toLowerCase( e.getKeyChar() );
        if ( keys.contains(c) )
            keys.remove( keys.indexOf(c) );
    }

    public static boolean isPressed(char key) { return keys.contains(key); }

    private void setFullScreen() {
        window.dispose();
        window.setResizable(false);
        window.setUndecorated(true);
        dev.setFullScreenWindow(window);
    }

    private void setNormal() {
        window.dispose();
        window.setResizable(true);
        dev.setFullScreenWindow(null);
        window.setUndecorated(false);
        window.setVisible(true);
    }
}

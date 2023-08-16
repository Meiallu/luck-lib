package org.meiallu.util.input;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.meiallu.system.Instance;

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
        if ( !keys.contains( e.getKeyChar() ))
            keys.add(keys.size(), e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keys.contains( e.getKeyChar() ))
            keys.remove(keys.indexOf( e.getKeyChar() ));
    }

    public static boolean isPressed(char key) { return ( keys.contains(key) ); }

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

package org.meiallu.util.input;

import java.util.List;

import javax.swing.JFrame;

import org.meiallu.system.Instance;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
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
    }

    @Override
    public void keyReleased(KeyEvent e) {}

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

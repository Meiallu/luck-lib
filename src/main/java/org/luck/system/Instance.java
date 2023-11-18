package org.luck.system;

import org.luck.util.Keyboard;
import org.luck.util.Mouse;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class Instance {

    private static JFrame frame;
    private static Canvas canvas;

    public Instance(String title, int w, int h) {
        Camera.getViewport().setSize(w, h);
        Camera.setX(w / 2d);
        Camera.setY(h / 2d);

        frame = new JFrame(title);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        int offx = frame.getWidth() - frame.getRootPane().getWidth();
        int offy = frame.getHeight() - frame.getRootPane().getHeight();
        frame.setMinimumSize(new Dimension(w + offx, h + offy));
        frame.setLocationRelativeTo(null);

        canvas = new Canvas();
        new Keyboard();
        new Mouse();
    }

    public static JFrame getWindow() {
        return frame;
    }

    public static Canvas getCanvas() {
        return canvas;
    }
}

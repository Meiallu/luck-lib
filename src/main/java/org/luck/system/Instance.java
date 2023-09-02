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
        Camera.getView().setSize(w, h);

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setVisible(true);

        int offx = frame.getWidth() - frame.getRootPane().getWidth();
        int offy = frame.getHeight() - frame.getRootPane().getHeight();
        frame.setMinimumSize( new Dimension(w + offx, h + offy) );
        frame.setLocationRelativeTo(null);

        new Game();
        new Keyboard();
        new Mouse();
        canvas = new Canvas();
    }

    public static JFrame getWindow() { return frame; }
    public static Canvas getCanvas() { return canvas; }
}

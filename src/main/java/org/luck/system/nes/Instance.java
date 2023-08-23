package org.luck.system.nes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.luck.util.Keyboard;

public class Instance {
    private static JFrame frame;
    private static int width = 500;
    private static int height = 500;
    private static JPanel panel;
    
    public Instance(String title, int w, int h) {
        width = w;
        height = h;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize( new Dimension(w + 16, h + 39) );
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setLayout( new BorderLayout() );
        frame.setLocationRelativeTo(null);

        panel = new Canvas();
        new Keyboard();
        new Game();
        
        frame.setVisible(true);
    }

    public static JFrame getWindow() { return frame; }
    public static int getWidth() { return width; }
    public static int getHeight() { return height; }
    public static JPanel getPanel() { return panel; }
}

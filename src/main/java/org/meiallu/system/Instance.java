package org.meiallu.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import org.meiallu.util.input.Keyboard;

public class Instance {
    private static JFrame frame;
    public static int width = 500;
    public static int height = 500;
    
    public Instance(String title, int w, int h) {
        width = w;
        height = h;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize( new Dimension(w + 16, h + 39) );
        frame.getContentPane().setBackground( Color.BLACK );
        frame.getContentPane().setLayout( new BorderLayout() );
        frame.setLocationRelativeTo(null);

        new Canvas();
        new Keyboard();
        new Game();
        
        frame.setVisible(true);
    }

    public static JFrame getWindow() { return frame; }
}

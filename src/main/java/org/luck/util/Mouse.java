package org.luck.util;

import org.luck.system.Instance;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    public static final int LEFT_BUTTON = 1;
    public static final int MIDDLE_BUTTON = 2;
    public static final int RIGHT_BUTTON = 3;

    public Mouse() { Instance.getWindow().addMouseListener(this); }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    public boolean isHolding(int button) { return false; }

    public boolean onPressAnyKey(int button) { return false; }
    public boolean onPress(int button) { return false; }

    public boolean onRelease(int button) { return false; }

    public static double getX() {
        double pointer = MouseInfo.getPointerInfo().getLocation().getX();
        double offset = Instance.getCanvas().getOffset().getX();
        double win = Instance.getWindow().getX() + Instance.getWindow().getRootPane().getX();
        double scale = Instance.getCanvas().getScale();
        return (pointer - (win - offset)) / scale;
    }

    public static double getY() {
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

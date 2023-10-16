package org.luck.util;

import org.luck.system.Instance;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Mouse implements MouseListener {

    public static final int LEFT_BUTTON = 1;
    public static final int MIDDLE_BUTTON = 2;
    public static final int RIGHT_BUTTON = 3;
    private static List<Integer> buttons = new ArrayList<>();
    private static int pressed = 0;
    private static int released = 0;

    public Mouse() {Instance.getWindow().addMouseListener(this);}

    public static boolean onPressAnyKey(int button) {return (pressed != 0);}

    public static boolean onPress(int button) {
        boolean i = (pressed == button);
        if (i)
            pressed = 0;
        return i;
    }

    public static boolean onRelease(int button) {
        boolean i = (released == button);
        if (i)
            released = 0;
        return i;
    }

    public static boolean isHolding(int button) {return buttons.contains(button);}

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

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = e.getButton();
        if (!buttons.contains(e.getButton()))
            buttons.add(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        released = e.getButton();
        if (buttons.contains(e.getButton()))
            buttons.remove(buttons.indexOf(e.getButton()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

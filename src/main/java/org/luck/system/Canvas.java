package org.luck.system;

import org.luck.util.Device;
import org.luck.util.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("unused")
public class Canvas extends JPanel {

    private static Scale type = Scale.SCALE_OUT;
    private static double precision = 1;
    private static byte tick = 1;
    private float scale = 1;
    private double offX = 0;
    private double offY = 0;

    private int lastW;
    private int lastH;

    public Canvas() {
        super.setBackground(Color.BLACK);
        super.setBounds(0, 0, 1, 1);
        Instance.getWindow().add(this);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                adjust();
                repaint();
            }
        }, Device.refreshTime, Device.refreshTime);
    }

    public static Scale getType() {return type;}

    public static void setType(Scale t) {type = t;}

    public static double getLetterboxPrecision() {return precision;}

    public static void setLetterboxPrecision(double pres) {precision = pres;}

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        Game.getScene().getLayers().forEach(l -> {
            if (l.isVisible()) {
                l.getObjects().forEach(o -> {
                    if (o.isVisible()) {
                        if (tick % (Device.refreshRate / o.getSpeed()) == 0) {
                            if (o.getSprite().getFrames().size() == o.getFrame() + 1)
                                o.setFrame(0);
                            else
                                o.setFrame(o.getFrame() + 1);
                        }
                        double x = o.getAbX();
                        double y = o.getAbY();

                        float width = o.getAbWidth();
                        float height = o.getAbHeight();

                        Image img = o.getSprite().getFrame(o.getFrame());

                        x -= (offX / scale) + Camera.getX() - (Camera.getViewX() / 2);
                        y -= (offY / scale) + Camera.getY() - (Camera.getViewY() / 2);

                        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, o.getOpacity()));
                        g2D.drawImage(img, (int) (x * scale), (int) (y * scale), (int) (width * scale), (int) (height * scale), null);
                    }
                });

                l.getTexts().forEach(t -> {
                    if (t.isVisible()) {
                        double xOff = (offX / scale) + Camera.getX();
                        double yOff = (offX / scale) + Camera.getX();

                        double x = (t.getX() - xOff) * scale;
                        double y = (t.getY() - yOff) * scale;

                        g2D.setColor(t.getColor());
                        g2D.setFont(new Font(t.getFont(), t.getStyle(), (int) (t.getSize() * scale)));
                        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, t.getOpacity()));
                        g2D.drawString(t.getText(), (int) x, (int) y);
                    }
                });
            }
        });
        if (tick == Device.refreshRate)
            tick = 1;
        else
            tick++;
    }

    public float getScale() {return scale;}

    public JPanel getPanel() {return this;}

    public Vector2D getOffset() {return new Vector2D(offX, offY);}

    private void adjust() {
        int rootX = Instance.getWindow().getRootPane().getWidth();
        int rootY = Instance.getWindow().getRootPane().getHeight();

        if (lastW == rootX && lastH == rootY)
            return;

        double viewX = Camera.getViewX();
        double viewY = Camera.getViewY();
        lastW = rootX;
        lastH = rootY;
        scale = 1;

        switch (type) {
            case SCALE_OUT:
                while (viewX * scale < rootX || viewY * scale < rootY)
                    scale++;
                super.setSize(rootX, rootY);
                break;
            case SCALE_IN:
                while (viewX * (scale + 1) < rootX || viewY * (scale + 1) < rootY)
                    scale++;
                super.setSize(rootX, rootY);
                break;
            case LETTERBOX:
                while (viewX * (scale + precision) < rootX && viewY * (scale + precision) < rootY)
                    scale += precision;

                int ww = rootX / 2;
                int hw = rootY / 2;
                int cw = (int) (viewX * scale) / 2;
                int ch = (int) (viewY * scale) / 2;

                super.setSize((int) (viewX * scale), (int) (viewY * scale));
                super.setLocation(ww - cw, hw - ch);
        }
        offX = (viewX * scale) / 2 - this.getWidth() / 2d;
        offY = (viewY * scale) / 2 - this.getHeight() / 2d;
    }
}


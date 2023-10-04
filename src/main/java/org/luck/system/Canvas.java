package org.luck.system;

import org.luck.type.Object;
import org.luck.type.Text;
import org.luck.util.Device;
import org.luck.util.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class Canvas extends JPanel {

    public static final byte SCALE_OUT = 1;
    public static final byte SCALE_IN = 2;
    public static final byte LETTERBOX = 3;
    private static byte type = 3;
    private static double letterbox_precision = 1;
    private static byte tick = 1;
    private float scale = 1;
    private double offX = 0;
    private double offY = 0;

    public Canvas() {
        super.setBackground(Color.BLACK);
        super.setBounds(0, 0, 1, 1);
        Instance.getWindow().add(this);

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            Adjust();
            repaint();
        }, Device.refreshTime, Device.refreshTime, TimeUnit.MILLISECONDS);
    }

    public static byte getType() { return type; }

    public static void setType(byte t) { type = t; }

    public static double getLetterboxPrecision() { return letterbox_precision; }

    public static void setLetterboxPrecision(double pres) { letterbox_precision = pres; }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        for (Layer l : Game.getScene().getLayers()) {
            if (l.isVisible()) {
                for (Object o : l.getObjects()) {
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
                }

                for (Text t : l.getTexts()) {
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
                }
            }
        }
        if (tick == Device.refreshRate)
            tick = 1;
        else
            tick++;
    }

    public float getScale() { return scale; }

    public JPanel getPanel() { return this; }

    public Vector2D getOffset() { return new Vector2D(offX, offY); }

    private void Adjust() {
        scale = 1;

        switch (type) {
            case SCALE_OUT:
                while (Camera.getViewX() * scale < Instance.getWindow().getRootPane().getWidth() || Camera.getViewY() * scale < Instance.getWindow().getRootPane().getHeight())
                    scale++;
                super.setSize(Instance.getWindow().getRootPane().getWidth(), Instance.getWindow().getRootPane().getHeight());
                break;
            case SCALE_IN:
                while (Camera.getViewX() * (scale + 1) < Instance.getWindow().getRootPane().getWidth() || Camera.getViewY() * (scale + 1) < Instance.getWindow().getRootPane().getHeight())
                    scale++;
                super.setSize(Instance.getWindow().getRootPane().getWidth(), Instance.getWindow().getRootPane().getHeight());
                break;
            case LETTERBOX:
                while (Camera.getViewX() * (scale + letterbox_precision) < Instance.getWindow().getRootPane().getWidth() && Camera.getViewY() * (scale + letterbox_precision) < Instance.getWindow().getRootPane().getHeight())
                    scale += letterbox_precision;

                int ww = Instance.getWindow().getRootPane().getWidth() / 2;
                int hw = Instance.getWindow().getRootPane().getHeight() / 2;
                int cw = (int) (Camera.getViewX() * scale) / 2;
                int ch = (int) (Camera.getViewY() * scale) / 2;

                super.setSize((int) (Camera.getViewX() * scale), (int) (Camera.getViewY() * scale));
                super.setLocation(ww - cw, hw - ch);
        }
        offX = (Camera.getViewX() * scale) / 2 - this.getWidth() / 2d;
        offY = (Camera.getViewY() * scale) / 2 - this.getHeight() / 2d;
    }
}

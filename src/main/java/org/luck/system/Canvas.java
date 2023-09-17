package org.luck.system;

import org.luck.listener.Luck;
import org.luck.type.Object;
import org.luck.type.Text;
import org.luck.util.Device;
import org.luck.util.Util;
import org.luck.util.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("unused")
public class Canvas extends JPanel {
    private static byte sType = 1;
    private static double letterbox_precision = 0.01;

    public static final byte SCALE_OUT = 1;
    public static final byte SCALE_IN = 2;
    public static final byte LETTERBOX = 3;

    private static byte tick = 1;
    private float scale = 1;
    private double offX = 0;
    private double offY = 0;
    private int lastWidth = 0;
    private int lastHeight = 0;
    private static boolean resizable = true;
    private static boolean offsetable = true;

    public Canvas() {
        super.setBackground(Color.BLACK);
        super.setBounds(0, 0, 1, 1);
        Instance.getWindow().add(this);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Adjust();
                repaint();
            }
        }, 0, (1000 / Device.refreshRate));
    }

    public static void setType(byte type) { sType = type; }
    public static byte getType() { return sType; }

    public static void setLetterboxPrecision(double pres) { letterbox_precision = pres; }
    public static double getLetterboxPrecision() { return letterbox_precision; }

    public static void setResizable(boolean stt) { resizable = stt; }
    public static void setOffsetable(boolean stt) { offsetable = stt; }

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
                        Image img = o.getSprite().getFrame( o.getFrame() );
                        img = Util.getRotatedImage(img, o.getAngle(), o.getAbWidth(), o.getAbHeight());
                        o.setAngle( o.getAngle() + 0.01f );

                        double x = o.getAbX();
                        double y = o.getAbY();
                        float width = img.getWidth(null);
                        float height = img.getHeight(null);

                        x -= (offX / scale) + Camera.getX() - (Camera.getViewX() / 2);
                        y -= (offY / scale) + Camera.getY() - (Camera.getViewY() / 2);

                        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, o.getOpacity()));
                        g2D.drawImage(img, (int) (x * scale), (int) (y * scale), (int) (width * scale), (int) (height * scale), null);
                    }
                }

                for (Text t : l.getTexts()) {
                    if (t.isVisible()) {
                        double x = t.getX();
                        double y = t.getY();

                        if (t.isOffsetable()) {
                            x -= (offX / scale) + Camera.getX();
                            y -= (offY / scale) + Camera.getY();
                        }

                        g2D.setColor(t.getColor());
                        g2D.setFont(new Font(t.getFont(), t.getStyle(), (int) (t.getSize() * scale)));
                        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, t.getOpacity()));
                        g2D.drawString(t.getText(), (int) (x * scale), (int) (y * scale));
                    }
                }
            }
        }
        if (tick == Device.refreshRate) tick = 1;
        else tick++;
    }

    public float getScale() { return scale; }
    public JPanel getPanel() { return this; }

    public Vector2D getOffset() { return new Vector2D(offX, offY); }

    private void Adjust() {
        if (Instance.getWindow().getHeight() != lastHeight || Instance.getWindow().getWidth() != lastWidth) {
            if (resizable) {
                scale = 1;
                int wWin; int hWin;
                int wCanvas; int hCanvas;

                switch (sType) {
                    case SCALE_OUT:
                        while ( Camera.getViewX() * scale < Instance.getWindow().getRootPane().getWidth()  ||
                                Camera.getViewY() * scale < Instance.getWindow().getRootPane().getHeight() ) {
                            scale++;
                        }
                    case SCALE_IN:
                        while ( Camera.getViewX() * (scale + 1) < Instance.getWindow().getRootPane().getWidth()  ||
                                Camera.getViewY() * (scale + 1) < Instance.getWindow().getRootPane().getHeight() ) {
                            scale++;
                        }
                    case LETTERBOX:
                        while ( Camera.getViewX() * (scale + letterbox_precision) < Instance.getWindow().getRootPane().getWidth()  &&
                                Camera.getViewY() * (scale + letterbox_precision) < Instance.getWindow().getRootPane().getHeight() ) {
                            scale += letterbox_precision;
                        }
                        wWin = Instance.getWindow().getRootPane().getWidth() / 2;
                        hWin = Instance.getWindow().getRootPane().getHeight() / 2;
                        wCanvas = (int) (Camera.getViewX() * scale) / 2;
                        hCanvas = (int) (Camera.getViewY() * scale) / 2;

                        super.setSize( (int) (Camera.getViewX() * scale), (int) (Camera.getViewY() * scale) );
                        super.setLocation(wWin - wCanvas, hWin - hCanvas);
                }
            }
            if (offsetable) {
                offX = (Camera.getViewX() * scale) / 2 - this.getWidth() / 2;
                offY = (Camera.getViewY() * scale) / 2 - this.getHeight() / 2;
            }
        }
    }
}

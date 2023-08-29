package org.luck.system.nes;

import org.luck.system.type.Object;
import org.luck.system.type.Text;
import org.luck.util.Device;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Canvas extends JPanel {
    private static byte tick = 1;
    private static boolean resizable = true;
    private static boolean offsetable = true;
    private short scale = 1;
    private int offX = 0;
    private int offY = 0;

    public Canvas() {
        super.setBackground(Color.BLACK);
        Instance.getWindow().add(this);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, (1000 / Device.refreshRate));
    }

    public static Image getMirroredImage(Image img) {
        BufferedImage b = (BufferedImage) img;
        BufferedImage i = new BufferedImage(
                b.getWidth(),
                b.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        for (int x = 1; x < b.getWidth(); x++) {
            for (int y = 1; y < b.getHeight(); y++) {
                int xx = b.getWidth() - x;
                int color = b.getRGB(x, y);
                i.setRGB(xx, y, color);
            }
        }
        return i;
    }

    public static Image getFlippedImage(Image img) {
        BufferedImage b = (BufferedImage) img;
        BufferedImage i = new BufferedImage(
                b.getWidth(),
                b.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        for (int x = 1; x < b.getWidth(); x++) {
            for (int y = 1; y < b.getHeight(); y++) {
                int yy = b.getHeight() - y;
                int color = b.getRGB(x, y);
                i.setRGB(x, yy, color);
            }
        }
        return i;
    }

    public static void setResizable(boolean stt) { resizable = stt; }
    public static void setOffsetable(boolean stt) { offsetable = stt; }

    public void paintComponent(Graphics g) {
        Adjust();
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        for (Layer l : Game.getScene().getLayers()) {
            if (l.isVisible()) {
                for (Object o : l.getObjects()) {
                    if (o.isVisible()) {
                        if (tick % (Device.refreshRate / o.getSpeed()) == 0) {
                            if (o.getAnimation().getFrames().size() == o.getFrame() + 1)
                                o.setFrame(0);
                            else
                                o.setFrame(o.getFrame() + 1);
                        }
                        Image img = o.getAnimation().getFrame(o.getFrame());
                        int x = (int) (o.getAbX() * scale);
                        int y = (int) (o.getAbY() * scale);
                        int width = (int) ((o.getWidth() * o.getScale()) * scale);
                        int height = (int) ((o.getHeight() * o.getScale()) * scale);

                        if (o.isMirrored()) img = getMirroredImage(img);
                        if (o.isFlipped()) img = getFlippedImage(img);

                        if (o.isOffsetable()) {
                            x -= (int) (offX + (Camera.getRealX() * scale));
                            y -= (int) (offY + (Camera.getRealY() * scale));
                        }

                        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, o.getOpacity()));
                        g2D.drawImage(img, x, y, width, height, null);
                    }
                }

                for (Text t : l.getTexts()) {
                    if (t.isVisible()) {
                        int x = (int) (t.getX() * scale);
                        int y = (int) (t.getY() * scale);

                        if (t.isOffsetable()) {
                            x -= offX + Camera.getX();
                            y -= offY + Camera.getY();
                        }

                        g2D.setColor(t.getColor());
                        g2D.setFont(new Font(t.getFont(), t.getStyle(), (t.getSize() * scale)));
                        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, t.getOpacity()));
                        g2D.drawString(t.getText(), x, y);
                    }
                }
            }
        }
        if (tick == 60) tick = 1;
        else tick++;
    }

    private void Adjust() {
        if (resizable) {
            scale = 1;
            while ((Instance.getWidth() * scale) < Instance.getWindow().getWidth() - 16 ||
                   (Instance.getHeight() * scale) < Instance.getWindow().getHeight() - 39) {
                scale++;
            }
        }
        if (offsetable) {
            offX = ((Instance.getWidth() * scale) / 2) - (this.getWidth() / 2);
            offY = ((Instance.getHeight() * scale) / 2) - (this.getHeight() / 2);
        }
    }
}

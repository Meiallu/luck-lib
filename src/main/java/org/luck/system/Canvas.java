package org.luck.system;

import org.luck.type.Object;
import org.luck.type.Text;
import org.luck.util.Device;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("unused")
public class Canvas extends JPanel {
    private static byte tick = 1;
    private static boolean resizable = true;
    private static boolean offsetable = true;
    private short scale = 1;
    private float offX = 0;
    private float offY = 0;

    private int lastWidth = 0;
    private int lastHeight = 0;

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
                        Image img = o.getAnimation().getFrame( o.getFrame() );
                        float x = o.getAbX();
                        float y = o.getAbY();
                        float width = o.getWidth() * o.getScale();
                        float height = o.getHeight() * o.getScale();

                        if ( o.isMirrored() ) { width = -width; x += o.getOrigin().getX(); }
                        if ( o.isFlipped() )  { height = -height; y += o.getOrigin().getY(); }

                        if ( o.isOffsetable() ) {
                            x -= (offX / scale) + Camera.getRealX();
                            y -= (offY / scale) + Camera.getRealY();
                        }

                        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, o.getOpacity()));
                        g2D.drawImage(img, (int) (x * scale), (int) (y * scale), (int) (width * scale), (int) (height * scale), null);
                    }
                }

                for (Text t : l.getTexts()) {
                    if (t.isVisible()) {
                        float x = t.getX();
                        float y = t.getY();

                        if (t.isOffsetable()) {
                            x -= (offX / scale) + Camera.getX();
                            y -= (offY / scale) + Camera.getY();
                        }

                        g2D.setColor(t.getColor());
                        g2D.setFont(new Font(t.getFont(), t.getStyle(), (t.getSize() * scale)));
                        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, t.getOpacity()));
                        g2D.drawString(t.getText(), (int) (x * scale), (int) (y * scale));
                    }
                }
            }
        }
        if (tick == Device.refreshRate) tick = 1;
        else tick++;
    }

    private void Adjust() {
        if (Instance.getWindow().getHeight() != lastHeight || Instance.getWindow().getWidth() != lastWidth) {
            if (resizable) {
                scale = 1;
                while (Instance.getWidth() * scale < Instance.getWindow().getWidth() - 16 ||
                       Instance.getHeight() * scale < Instance.getWindow().getHeight() - 39) {
                    scale++;
                }
            }
            if (offsetable) {
                offX = (Instance.getWidth() * scale) / 2 - this.getWidth() / 2;
                offY = (Instance.getHeight() * scale) / 2 - this.getHeight() / 2;
            }
        }
    }
}

package org.meiallu.system;

import org.meiallu.system.type.Object;
import org.meiallu.util.Camera;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    private int scale = 1;
    private static Camera cam = new Camera();
    private int offX = 0;
    private int offY = 0;

    private static boolean resizable = true;
    private static boolean drawable = true;
    private static boolean offsetable = true;

    public Canvas() {
        super.setBackground( Color.WHITE );
        Instance.getWindow().add(this);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                AdjustResizing();
                AdjustOffset();
                repaint();
            }
        }, 0, 16);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        if (drawable) {        
            for ( Object o : Game.getScene().getObjects() ) {
                if ( o.isVisible() ) {
                    g2D.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, o.getOpacity() ) );

                    Image img = o.getAnimation().getFrame( o.getFrame() );
                    int x = (int) ( o.getX() * scale );
                    int y = (int) ( o.getY() * scale );

                    if ( o.isOffsetable() )
                        x -= offX + cam.getX();
                        y -= offY + cam.getY();
                        
                    int width = (int) ( ( o.getWidth() * o.getScale() ) * scale );
                    int height = (int) ( ( o.getHeight() * o.getScale() ) * scale );

                    g2D.drawImage(img, x, y, width, height, null);
                }
            }
        }
    }

    public void AdjustResizing() {
        if (resizable)
            scale = 1;
            while ( (Instance.width * scale) < Instance.getWindow().getWidth() - 16
                || (Instance.height * scale) < Instance.getWindow().getHeight() - 39 ) {
                scale ++;
            }
    }

    public void AdjustOffset() {
        if (offsetable)
            offX = ( (Instance.width * scale) / 2 ) - (this.getWidth() / 2);
            offY = ( (Instance.height * scale) / 2 ) - (this.getHeight() / 2);
    }

    public static void setCamera(Camera camera) { cam = camera; }
    public static Camera getCamera() { return cam; }

    public static void setResizable(boolean stt) { resizable = stt; }
    public static void setDrawable(boolean stt) { drawable = stt; }
    public static void setOffsetable(boolean stt) { offsetable = stt; }
}

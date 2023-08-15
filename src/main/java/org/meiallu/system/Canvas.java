package org.meiallu.system;

import org.meiallu.system.type.Object;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    private static float scale = 1.0f;
    private static int OffsetX = 0;
    private static int OffsetY = 0;

    public Canvas() {
        super.setBackground( Color.WHITE );
        Instance.getWindow().add(this);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                AdjustResizing();
                repaint();
            }
        }, 0, 16);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        for ( Object o : Game.getScene().getObjects() ) {
            if ( o.isVisible() ) {
                g2D.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, o.getOpacity() ) );

                Image img = o.getAnimation().getFrame( o.getFrame() );
                int x = (int) ( o.getX() - (OffsetX * scale) );
                int y = (int) ( o.getY() - (OffsetY * scale) );
                int width = (int) ( ( o.getWidth() * o.getScale() ) * scale );
                int height = (int) ( ( o.getHeight() * o.getScale() ) * scale );

                g2D.drawImage(img, x, y, width, height, null);
            }
        }
    }

    public void AdjustResizing() {
        
    }
}

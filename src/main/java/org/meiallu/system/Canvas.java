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
    private int scale = 1;
    private int offX = 0;
    private int offY = 0;

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

        for ( Object o : Game.getScene().getObjects() ) {
            if ( o.isVisible() ) {
                g2D.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, o.getOpacity() ) );

                Image img = o.getAnimation().getFrame( o.getFrame() );
                int x = (int) ( o.getX() * scale ) - offX;
                int y = (int) ( o.getY() * scale ) - offY;
                int width = (int) ( ( o.getWidth() * o.getScale() ) * scale );
                int height = (int) ( ( o.getHeight() * o.getScale() ) * scale );

                g2D.drawImage(img, x, y, width, height, null);
            }
        }
    }

    public void AdjustResizing() {
        scale = 1;
        while ( (Instance.width * scale) < Instance.getWindow().getWidth() - 16
              || (Instance.height * scale) < Instance.getWindow().getHeight() - 39 ) {
            scale ++;
        }
    }

    public void AdjustOffset() {
        offX = ( (Instance.width * scale) / 2 ) - (this.getWidth() / 2);
        offY = ( (Instance.height * scale) / 2 ) - (this.getHeight() / 2);
    }
}

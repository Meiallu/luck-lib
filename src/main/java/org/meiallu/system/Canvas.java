package org.meiallu.system;

import org.meiallu.system.type.Object;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class Canvas extends JPanel {

    public Canvas() {
        super.setBackground( Color.WHITE );
        Instance.getWindow().add(this);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Adjust();
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
                int x = (int) o.getX();
                int y = (int) o.getY();
                int width = (int) ( o.getWidth() * o.getScale() );
                int height = (int) ( o.getHeight() * o.getScale() );

                g2D.drawImage(img, x, y, width, height, null);
            }
        }
    }

    public void Adjust() {
        super.setSize(Instance.width, Instance.height);
        int x = (Instance.getWindow().getWidth() / 2) - (Instance.width / 2);
        int y = (Instance.getWindow().getHeight() / 2) - (Instance.height / 2);
        super.setLocation(x - 8, y - 19);
    }
}

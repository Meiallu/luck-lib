package org.luck.system.nes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

import org.luck.system.type.Object;
import org.luck.util.Camera;
import org.luck.util.Text;

public class Canvas extends JPanel {
    private int scale = 1;
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
            for ( Layer l : Game.getScene().getLayers() ) {            
                for ( Object o : l.getObjects() ) {
                    if ( o.isVisible() ) {
                        g2D.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, o.getOpacity() ) );

                        Image img = o.getAnimation().getFrame( o.getFrame() );
                        int x = (int) ( o.getX() * scale );
                        int y = (int) ( o.getY() * scale );

                        if ( o.isOffsetable() )
                            x -= offX + (Camera.getX() * scale);
                            y -= offY + (Camera.getY() * scale);
                            
                        int width = (int) ( ( o.getWidth() * o.getScale() ) * scale );
                        int height = (int) ( ( o.getHeight() * o.getScale() ) * scale );

                        g2D.drawImage(img, x, y, width, height, null);
                    }
                }

                for ( Text t : l.getTexts() ) {
                    if ( t.isVisible() ) {
                        g2D.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, t.getOpacity() ) );

                        int x = (int) ( t.getX() * scale );
                        int y = (int) ( t.getY() * scale );

                        if ( t.isOffsetable() )
                            x -= offX + Camera.getX();
                            y -= offY + Camera.getY();

                        g2D.setColor( t.getColor() );
                        g2D.setFont( new Font( t.getFont(), t.getStyle(), (t.getSize() * scale) ) );
                        g2D.drawString(t.getText(), x, y);
                    }
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

    public static void setResizable(boolean stt) { resizable = stt; }
    public static void setDrawable(boolean stt) { drawable = stt; }
    public static void setOffsetable(boolean stt) { offsetable = stt; }
}

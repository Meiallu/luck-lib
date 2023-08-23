package org.luck.system.nes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import org.luck.system.type.Object;
import org.luck.util.Camera;
import org.luck.util.Text;

public class Canvas extends JPanel {
    private static byte tick = 1;

    private short scale = 1;
    private int offX = 0;
    private int offY = 0;

    private static boolean resizable = true;
    private static boolean offsetable = true;

    public Canvas() {
        super.setBackground(Color.BLACK);
        Instance.getWindow().add(this);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() { repaint(); }
        }, 0, 16);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);
        Adjust();

        for ( Layer l : Game.getScene().getLayers() ) {            
            for ( Object o : l.getObjects() ) {
                if ( o.isVisible() ) {
                    if (tick % ( 60 / o.getSpeed() ) == 0) {
                        if (o.getAnimation().getFrames().size() == o.getFrame() + 1)
                            o.setFrame(0);
                        else
                            o.setFrame(o.getFrame() + 1);
                    }
                    Image img = o.getAnimation().getFrame( o.getFrame() );
                    int x = (int) ( o.getX() * scale );
                    int y = (int) ( o.getY() * scale );                        
                    int width = (int) ( ( o.getWidth() * o.getScale() ) * scale );
                    int height = (int) ( ( o.getHeight() * o.getScale() ) * scale );

                    if ( o.isMirrored() ) img = getMirroredImage(img);
                    if ( o.isFlipped() ) img = getFlippedImage(img);
                    if ( o.isOffsetable() )
                        x -= offX + (Camera.getRealX() * scale);
                        y -= offY + (Camera.getRealY() * scale);

                    g2D.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, o.getOpacity() ) );
                    g2D.drawImage(img, x, y, width, height, null);
                }
            }

            for ( Text t : l.getTexts() ) {
                if ( t.isVisible() ) {
                    int x = (int) ( t.getX() * scale );
                    int y = (int) ( t.getY() * scale );

                    if ( t.isOffsetable() )
                        x -= offX + Camera.getX();
                        y -= offY + Camera.getY();

                    g2D.setColor( t.getColor() );
                    g2D.setFont( new Font( t.getFont(), t.getStyle(), (t.getSize() * scale) ) );
                    g2D.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, t.getOpacity() ) );
                    g2D.drawString(t.getText(), x, y);
                }
            }
        }
        if (tick == 60) tick = 1; else tick++;
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
        return (Image) i;
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
        return (Image) i;
    }

    private void Adjust() {
        if (resizable)
            scale = 1;
            while ( (Instance.getWidth() * scale) < Instance.getWindow().getWidth() - 16
                || (Instance.getHeight() * scale) < Instance.getWindow().getHeight() - 39 ) {
                scale ++;
            }
        if (offsetable)
            offX = ( (Instance.getWidth() * scale) / 2 ) - (this.getWidth() / 2);
            offY = ( (Instance.getHeight() * scale) / 2 ) - (this.getHeight() / 2);
    }

    public static void setResizable(boolean stt) { resizable = stt; }
    public static void setOffsetable(boolean stt) { offsetable = stt; }
}

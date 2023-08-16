package org.luck.system.type;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Animation {
    private List<Image> frames = new ArrayList<Image>();
    private float speed = 5.0f;

    public void setupFrames(String folder, String prefix, String suffix) {
        int a = 1;
        File f = new File(folder + prefix + a + suffix);
        while ( f.exists() ) {
            Image img;
            try {
                img = ImageIO.read(f);
                frames.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
            a++;
            f = new File(folder + prefix + a + suffix);
        }
    }

    public void addFrame(String path) {
        File f = new File(path);
        if ( f.exists() ) {
            Image img;
            try {
                img = ImageIO.read(f);
                frames.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addFrame(Image img) {
        frames.add(img);
    }

    public void addFrame(File file) {
        if ( file.exists() ) {
            Image img;
            try {
                img = ImageIO.read(file);
                frames.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeFrame(int index) {
        frames.remove(index);
    }

    public void removeFrame(Image img) {
        frames.remove(img);
    }

    public Image getFrame(int index) {
        return frames.get(index);
    }

    public List<Image> getFrames() {
        return frames;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float spd) {
        speed = spd;
    }
}

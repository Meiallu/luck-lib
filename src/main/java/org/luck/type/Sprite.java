package org.luck.type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Sprite {

    private List<Image> frames = new ArrayList<>();
    private byte speed = 5;

    public Sprite setupFrames(String folder, String prefix, String suffix) {
        int a = 1;
        File f = new File(folder + prefix + a + suffix);
        while (f.exists()) {
            Image img = null;
            try {
                img = ImageIO.read(f);
                frames.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
            a++;
            f = new File(folder + prefix + a + suffix);
        }
        return this;
    }

    public Sprite addFrame(Image img) {
        frames.add(img);
        return this;
    }

    public Sprite addFrame(String path) {
        File f = new File(path);
        if (f.exists()) {
            Image img;
            try {
                img = ImageIO.read(f);
                frames.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public Sprite addFrame(File file) {
        if (file.exists()) {
            Image img;
            try {
                img = ImageIO.read(file);
                frames.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public Sprite addFrame(Image img, int index) {
        frames.add(index, img);
        return this;
    }

    public Sprite addFrame(String path, int index) {
        File f = new File(path);
        if (f.exists()) {
            Image img;
            try {
                img = ImageIO.read(f);
                frames.add(index, img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public Sprite addFrame(File file, int index) {
        if (file.exists()) {
            Image img;
            try {
                img = ImageIO.read(file);
                frames.add(index, img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public Sprite removeFrame(int index) {
        frames.remove(index);
        return this;
    }

    public Sprite removeFrame(Image img) {
        frames.remove(img);
        return this;
    }

    public Image getFrame(int index) {
        return frames.get(index);
    }

    public List<Image> getFrames() {
        return frames;
    }

    public byte getSpeed() {
        return speed;
    }

    public Sprite setSpeed(int fps) {
        speed = (byte) fps;
        return this;
    }
}
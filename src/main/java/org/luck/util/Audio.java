package org.luck.util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
    private AudioInputStream str;
    private float vol = 0.0f;
    private Clip c;
    private long time;

    public Audio() {} 

    public Audio(String path) {
        try {
            str = AudioSystem.getAudioInputStream( new File(path).toURI().toURL() ); 
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }

    public AudioInputStream getStream() { return str; }

    public float getVolume() { return vol; }
    public void setVolume(float volume) { vol = volume; }

    public Clip getClip() { return c; }
    public void setClip(Clip clip) { c = clip; }

    public void pause() { 
        time = c.getMicrosecondPosition();
        c.stop();
    }

    public void resume() {
        c.setMicrosecondPosition(time);
        c.start();
    }

    public void setSecond(float sec) {
        c.setMicrosecondPosition( (long) sec * 1000000 );
    }

    public void loop(int count) { c.loop(count); }

    public boolean isOpen() { return c.isOpen(); }
    public boolean isRunning() { return c.isRunning(); }
    public boolean isActive() { return c.isActive(); }
}

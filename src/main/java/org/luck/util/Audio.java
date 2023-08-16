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
}

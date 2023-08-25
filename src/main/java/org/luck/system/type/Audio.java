package org.luck.system.type;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
    private float vol = 0.0f;
    private long time;
    private Clip clip;
    private AudioInputStream str;

    public Audio(String path) {
        try {
            str = AudioSystem.getAudioInputStream( new File(path).toURI().toURL() );
            clip = AudioSystem.getClip();
            clip.open(str); 
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) { 
            e.printStackTrace(); 
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public AudioInputStream getStream() { return str; }

    public float getVolume() { return vol; }
    public void setVolume(float volume) { vol = volume; }

    public Clip getClip() { return clip; }
    public void setClip(Clip clip) { this.clip = clip; }

    public void pause() { 
        time = clip.getMicrosecondPosition();
        clip.stop();
    }

    public void resume() {
        clip.setMicrosecondPosition(time);
        clip.start();
    }

    public void setSecond(float sec) {
        clip.setMicrosecondPosition( (long) sec * 1000000 );
    }

    public void loop(int count) { clip.loop(count); }

    public boolean isOpen() { return clip.isOpen(); }
    public boolean isRunning() { return clip.isRunning(); }
    public boolean isActive() { return clip.isActive(); }
}

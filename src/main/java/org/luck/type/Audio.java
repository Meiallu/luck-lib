package org.luck.type;

import org.luck.util.AudioManager;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class Audio {
    private float vol = 0.0f;
    private long time;
    private Clip clip;
    private AudioInputStream str;

    public Audio(String path) {
        try {
            str = AudioSystem.getAudioInputStream(new File(path).toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(str);
        } catch (UnsupportedAudioFileException |
                 IOException |
                 LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public AudioInputStream getStream() {
        return str;
    }

    public float getVolume() { return vol; }
    public void setVolume(float volume) { vol = volume; }

    public Clip getClip() { return clip; }
    public void setClip(Clip clip) { this.clip = clip; }

    public void pause() { AudioManager.pause(this); }
    public void resume() { AudioManager.resume(this); }

    public long getLastPauseMicrosecond() { return time; }
    public void setPauseMicrosecond(long i) { time = i; }

    public void setSecond(float sec) { clip.setMicrosecondPosition((long) sec * 1000000); }

    public void loop(int count) { clip.loop(count); }

    public boolean isOpen() { return clip.isOpen(); }
    public boolean isRunning() { return clip.isRunning(); }
    public boolean isActive() { return clip.isActive(); }

    public void play() { AudioManager.play(this); }
    public void stop() { AudioManager.stop(this); }
}

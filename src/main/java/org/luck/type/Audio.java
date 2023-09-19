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
    public Audio setVolume(float volume) { vol = volume; return this; }

    public Clip getClip() { return clip; }
    public Audio setClip(Clip clip) { this.clip = clip; return this; }

    public Audio pause() { AudioManager.pause(this); return this; }
    public Audio resume() { AudioManager.resume(this); return this; }

    public long getLastPauseMicrosecond() { return time; }
    public Audio setPauseMicrosecond(long i) { time = i; return this; }

    public Audio setSecond(float sec) {
        clip.setMicrosecondPosition((long) sec * 1000000);
        return this;
    }

    public Audio loop(int count) { clip.loop(count); return this; }

    public boolean isOpen() { return clip.isOpen(); }
    public boolean isRunning() { return clip.isRunning(); }
    public boolean isActive() { return clip.isActive(); }

    public Audio play() { AudioManager.play(this); return this; }
    public Audio stop() { AudioManager.stop(this); return this; }
}

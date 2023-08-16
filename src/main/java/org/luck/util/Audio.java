package org.luck.util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
    private AudioInputStream str;

    public Audio() {} 
    public Audio(String path) {
        try {
            File f = new File(path);
            str = AudioSystem.getAudioInputStream(f.toURI().toURL()); 
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AudioInputStream getStream() { return str; }
}

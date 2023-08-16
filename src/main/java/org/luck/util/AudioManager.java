package org.luck.util;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class AudioManager {

    public static void play(Audio a) {
        AudioInputStream ad = a.getStream();  
        Clip clip = null;

        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        
        try {
            clip.open(ad);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FloatControl gc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gc.setValue( a.getVolume() );

        clip.start();
    }
}

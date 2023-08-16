package org.luck.util;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class AudioManager {

    public static void play(Audio a) { 
        Clip clip = null;
        
        try {
            clip = AudioSystem.getClip();
            clip.open( a.getStream() );
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FloatControl gc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gc.setValue( a.getVolume() );

        a.setClip(clip);
        clip.start();
    }

    public static void stop(Audio a) {
        a.getClip().stop();
    }
}

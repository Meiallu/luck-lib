package org.luck.util;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import org.luck.system.type.Audio;

public class AudioManager {

    public static void play(Audio a) { 
        Clip clip = a.getClip();

        FloatControl gc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gc.setValue( a.getVolume() );

        a.setClip(clip);
        clip.start();
    }

    public static void stop(Audio a) {
        a.getClip().stop();
    }
}

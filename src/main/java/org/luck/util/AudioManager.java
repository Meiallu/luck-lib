package org.luck.util;

import org.luck.type.Audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

@SuppressWarnings("unused")
public class AudioManager {

    public static void play(Audio a) {
        Clip clip = a.getClip();

        FloatControl gc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gc.setValue(a.getVolume());

        a.setClip(clip);
        clip.start();
    }

    public static void stop(Audio a) {a.getClip().stop();}

    public static void resume(Audio a) {
        a.getClip().setMicrosecondPosition(a.getLastPauseMicrosecond());
        a.getClip().start();
    }

    public static void pause(Audio a) {
        a.setPauseMicrosecond(a.getClip().getMicrosecondPosition());
        a.getClip().stop();
    }
}

package com.aor.thief.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Objects;

public class SoundFactory {
    public Clip loadFile(String filePath){
        try {
            return new SoundLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().
                            getClassLoader().getResource(filePath))), AudioSystem.getClip());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LoopedSoundPlayer createBackgroundSound(){
        return new LoopedSoundPlayer(loadFile("sounds/background.wav"));
    }

    public InstanceSound createItemPickupSound(){
        return new InstanceSound(loadFile("sounds/itempickup.wav"));
    }

    public InstanceSound createFootStepSound(){
        return new InstanceSound(loadFile("sounds/footstep.wav"));
    }

    public InstanceSound createGunShotSound(){
        return new InstanceSound(loadFile("sounds/gunshot.wav"));
    }

    public InstanceSound createGuardDeathSound(){
        return new InstanceSound(loadFile("sounds/guarddeathsound.wav"));
    }

    public InstanceSound createMenuChangeSound(){
        return new InstanceSound(loadFile("sounds/menuchange.wav"));
    }

    public InstanceSound createMenuSelectSound(){
        return new InstanceSound(loadFile("sounds/menuselect.wav"));
    }
}

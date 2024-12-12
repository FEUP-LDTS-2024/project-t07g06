package com.aor.thief.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Objects;

public class SoundFactory {
    public Clip loadFile(String filePath){
        try {
            return new SoundLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource(filePath))), AudioSystem.getClip());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BackgroundSoundPlayer createBackgroundSound(){
        return new BackgroundSoundPlayer(loadFile("sounds/background.wav"));
    }

    public ItemPickupSound createItemPickupSound(){
        return new ItemPickupSound(loadFile("sounds/itempickup.wav"));
    }

    public FootStepSound createFootStepSound(){
        return new FootStepSound(loadFile("sounds/footstep.wav"));
    }

    public GunShot createGunShotSound(){
        return new GunShot(loadFile("sounds/gunshot.wav"));
    }

    public GuardDeathSoundPlayer createGuardDeathSound(){
        return new GuardDeathSoundPlayer(loadFile("sounds/guarddeathsound.wav"));
    }
}

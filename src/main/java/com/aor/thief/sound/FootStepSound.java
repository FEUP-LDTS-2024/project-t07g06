package com.aor.thief.sound;

import javax.sound.sampled.Clip;

public class FootStepSound implements SoundPlayer{
    private Clip sound;

    public FootStepSound(Clip sound) {
        this.sound = sound;
    }

    @Override
    public void start() {
        sound.setMicrosecondPosition(0);
        sound.start();

    }

    @Override
    public void stop() {
        sound.stop();
    }

    @Override
    public void setSound(Clip sound) {
        this.sound = sound;
    }

    @Override
    public Clip getSound() {
        return sound;
    }
}

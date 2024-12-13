package com.aor.thief.sound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SoundFactoryTests {
    private Clip mockClip;
    private SoundFactory soundFactory;
    private SoundLoader soundLoader;

    @BeforeEach
    void setUp() {
        mockClip = mock(Clip.class);
        soundFactory = Mockito.spy(new SoundFactory());

    }



    @Test
    void createBackgroundSound() {
        SoundFactory spyFactory = Mockito.spy(soundFactory);
        doReturn(mockClip).when(spyFactory).loadFile("sounds/background.wav");

        LoopedSoundPlayer backgroundSound = spyFactory.createBackgroundSound();

        assertNotNull(backgroundSound);
        assertNotNull(backgroundSound.getSound());
    }

    @Test
    void createItemPickupSound() {
        SoundFactory spyFactory = Mockito.spy(soundFactory);
        doReturn(mockClip).when(spyFactory).loadFile("sounds/itempickup.wav");

        InstanceSound itemPickupSound = spyFactory.createItemPickupSound();

        assertNotNull(itemPickupSound);
        assertNotNull(itemPickupSound.getSound());
    }

    @Test
    void createFootStepSound() {
        SoundFactory spyFactory = Mockito.spy(soundFactory);
        doReturn(mockClip).when(spyFactory).loadFile("sounds/footstep.wav");

        InstanceSound footStepSound = spyFactory.createFootStepSound();

        assertNotNull(footStepSound);
        assertNotNull(footStepSound.getSound());
    }

    @Test
    void createGunShotSound() {
        SoundFactory spyFactory = Mockito.spy(soundFactory);
        doReturn(mockClip).when(spyFactory).loadFile("sounds/gunshot.wav");

        InstanceSound gunShotSound = spyFactory.createGunShotSound();

        assertNotNull(gunShotSound);
        assertNotNull(gunShotSound.getSound());
    }

    @Test
    void createGuardDeathSound() {
        SoundFactory spyFactory = Mockito.spy(soundFactory);
        doReturn(mockClip).when(spyFactory).loadFile("sounds/guarddeathsound.wav");

        InstanceSound guardDeathSound = spyFactory.createGuardDeathSound();

        assertNotNull(guardDeathSound);
        assertNotNull(guardDeathSound.getSound());
    }

    @Test
    void createMenuChangeSound() {
        SoundFactory spyFactory = Mockito.spy(soundFactory);
        doReturn(mockClip).when(spyFactory).loadFile("sounds/menuchange.wav");

        InstanceSound menuChangeSound = spyFactory.createMenuChangeSound();

        assertNotNull(menuChangeSound);
        assertNotNull(menuChangeSound.getSound());
    }

    @Test
    void createMenuSelectSound() {
        SoundFactory spyFactory = Mockito.spy(soundFactory);
        doReturn(mockClip).when(spyFactory).loadFile("sounds/menuselect.wav");

        InstanceSound menuSelectSound = spyFactory.createMenuSelectSound();

        assertNotNull(menuSelectSound);
        assertNotNull(menuSelectSound.getSound());
    }
}

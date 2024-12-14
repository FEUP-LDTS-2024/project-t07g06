package com.aor.thief.sound;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class SoundLoaderTest {
    private SoundLoader soundLoader;
    private AudioInputStream audioInputStream;
    private Clip clip;

    @BeforeEach
    void setUp(){
        soundLoader=new SoundLoader();
        audioInputStream= Mockito.mock(AudioInputStream.class);
        clip=Mockito.mock(Clip.class);
    }

    @Test
    void loadSoundSucess() throws Exception {
        doNothing().when(clip).open(audioInputStream);
        Clip result=soundLoader.loadSound(audioInputStream,clip);
        assertEquals(result, clip);
    }

    @Test
    void loadSoundException() throws Exception {
        doThrow(new FileNotFoundException()).when(clip).open(Mockito.any());

        Exception exception= Assertions.assertThrows(Exception.class,
                ()->new SoundLoader().loadSound(audioInputStream,clip),"Exception");
        assertEquals("Unable to load sound file!",exception.getMessage());
    }
}

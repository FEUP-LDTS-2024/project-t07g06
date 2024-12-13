package com.aor.thief.sound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.Clip;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoopedSoundTest {
    private LoopedSoundPlayer loop;
    private Clip mock;

    @BeforeEach
    public void setUp(){
        mock= Mockito.mock(Clip.class);
        loop=new LoopedSoundPlayer(mock);
    }

    @Test
    public void startstop(){
        loop.start();
        loop.stop();
        Mockito.verify(mock).setMicrosecondPosition(0);
        Mockito.verify(mock).start();
        Mockito.verify(mock).loop(Clip.LOOP_CONTINUOUSLY);
        Mockito.verify(mock).stop();
    }

    @Test
    public void getset(){
        Clip newMock=Mockito.mock(Clip.class);
        loop.setSound(newMock);
        assertEquals(newMock, loop.getSound());
    }
}

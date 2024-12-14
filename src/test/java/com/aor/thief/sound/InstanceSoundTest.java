package com.aor.thief.sound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.Clip;

import static org.junit.jupiter.api.Assertions.assertEquals;

public  class InstanceSoundTest {
    private InstanceSound instanceSound;
    private Clip mock;

    @BeforeEach
    public void setUp(){
        mock= Mockito.mock(Clip.class);

        instanceSound=new InstanceSound(mock);
    }

    @Test
    public void playstop(){
        instanceSound.start();
        instanceSound.stop();
        Mockito.verify(mock).setMicrosecondPosition(0);
        Mockito.verify(mock).start();
        Mockito.verify(mock).stop();
    }

    @Test
    public void getset(){
        Clip newMock=Mockito.mock(Clip.class);
        instanceSound.setSound(newMock);
        assertEquals(newMock, instanceSound.getSound());
    }

}

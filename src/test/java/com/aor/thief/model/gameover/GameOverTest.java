package com.aor.thief.model.gameover;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOverTest {
    private GameOver gameOver;

    @BeforeEach
    void setUp(){
        gameOver=new GameOver();
    }

    @Test
    void testInitialSelection() {
        assertTrue(gameOver.isSelectedStart());
        assertFalse(gameOver.isSelectedExit());
    }

    @Test
    void testNextEntry() {
        gameOver.nextEntry();
        assertTrue(gameOver.isSelectedExit());
    }

    @Test
    void testPreviousEntry() {
        gameOver.nextEntry();
        gameOver.previousEntry();
        assertTrue(gameOver.isSelectedStart());
    }

    @Test
    void testLoopingNextEntry() {
        gameOver.nextEntry();
        gameOver.nextEntry();
        assertTrue(gameOver.isSelectedStart());
    }

    @Test
    void testLoopingPreviousEntry() {
        gameOver.previousEntry();
        assertTrue(gameOver.isSelectedExit());
    }

    @Test
    void testGetEntry() {
        assertEquals("Restart", gameOver.getEntry(0));
        assertEquals("Return to Main Menu", gameOver.getEntry(1));
    }

    @Test
    void testNumberEntries() {
        assertEquals(2, gameOver.getNumberEntries());
    }
}

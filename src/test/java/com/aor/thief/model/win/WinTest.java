package com.aor.thief.model.win;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinTest {
    private Win win;

    @BeforeEach
    void setUp(){
         win=new Win();
    }

    @Test
    void testInitialSelection() {
        assertTrue(win.isSelectedStart());
        assertFalse(win.isSelectedExit());
    }

    @Test
    void testNextEntry() {
        win.nextEntry();
        assertTrue(win.isSelectedExit());
    }

    @Test
    void testPreviousEntry() {
        win.nextEntry();
        win.previousEntry();
        assertTrue(win.isSelectedStart());
    }

    @Test
    void testLoopingNextEntry() {
        win.nextEntry();
        win.nextEntry();
        assertTrue(win.isSelectedStart());
    }

    @Test
    void testLoopingPreviousEntry() {
        win.previousEntry();
        assertTrue(win.isSelectedExit());
    }

    @Test
    void testGetEntry() {
        assertEquals("Restart", win.getEntry(0));
        assertEquals("Return to Main Menu", win.getEntry(1));
    }

    @Test
    void testNumberEntries() {
        assertEquals(2, win.getNumberEntries());
    }
}
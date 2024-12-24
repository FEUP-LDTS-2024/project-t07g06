package com.aor.thief.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private Menu menu;

    @BeforeEach
    void setUp(){
        menu=new Menu();
    }

    @Test
    void testInitialSelection() {
        assertTrue(menu.isSelectedStart());
        assertFalse(menu.isSelectedInstructions());
        assertFalse(menu.isSelectedExit());
    }

    @Test
    void testNextEntry() {
        menu.nextEntry();
        assertTrue(menu.isSelectedInstructions());
    }

    @Test
    void testPreviousEntry() {
        menu.nextEntry();
        menu.previousEntry();
        assertTrue(menu.isSelectedStart());
    }

    @Test
    void testLoopingNextEntry() {
        Menu menu = new Menu();
        menu.nextEntry();
        menu.nextEntry();
        menu.nextEntry();
        assertTrue(menu.isSelectedStart());
    }

    @Test
    void testLoopingPreviousEntry() {
        Menu menu = new Menu();
        menu.previousEntry();
        assertTrue(menu.isSelectedExit());
    }

    @Test
    void testGetEntry() {
        Menu menu = new Menu();
        assertEquals("Start", menu.getEntry(0));
        assertEquals("Instructions", menu.getEntry(1));
        assertEquals("Exit", menu.getEntry(2));
    }

    @Test
    void testNumberEntries() {
        Menu menu = new Menu();
        assertEquals(3, menu.getNumberEntries());
    }
}

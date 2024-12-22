package com.aor.thief.model.instructions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionsTest {
    private Instructions instructions;

    @BeforeEach
    void setUp(){
        instructions=new Instructions();
    }

    @Test
    void testInitialSelection() {
        assertTrue(instructions.isSelectedStart());
        assertFalse(instructions.isSelectedExit());
    }

    @Test
    void testNextEntry() {
        instructions.nextEntry();
        assertTrue(instructions.isSelectedExit());
    }

    @Test
    void testPreviousEntry() {
        instructions.nextEntry();
        instructions.previousEntry();
        assertTrue(instructions.isSelectedStart());
    }

    @Test
    void testLoopingNextEntry() {
        instructions.nextEntry();
        instructions.nextEntry();
        assertTrue(instructions.isSelectedStart());
    }

    @Test
    void testLoopingPreviousEntry() {
        instructions.previousEntry();
        assertTrue(instructions.isSelectedExit());
    }

    @Test
    void testGetEntry() {
        assertEquals("Start Game", instructions.getEntry(0));
        assertEquals("Return to Main Menu", instructions.getEntry(1));
    }

    @Test
    void testNumberEntries() {
        assertEquals(2, instructions.getNumberEntries()); // Verifica o número de entradas
    }
}
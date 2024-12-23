package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CopControllerTest {
    private Arena arena;
    private CopController copController;
    private Cop mockCop;
    private Thief mockThief;

    @BeforeEach
    void setUp() {
        arena = mock(Arena.class);
        copController = new CopController(arena);

        mockCop = mock(Cop.class);
        mockThief = mock(Thief.class);

        LinkedList<Cop> cops = new LinkedList<>();
        cops.add(mockCop);

        when(arena.getCops()).thenReturn(cops);
        when(arena.getThief()).thenReturn(mockThief);
    }

    @Test
    void testIsCaught() {

        when(arena.fov(mockCop)).thenReturn(new LinkedList<>());
        when(mockThief.getHitbox()).thenReturn(new LinkedList<>());

        // Test when the thief is not in the FOV
        assertFalse(copController.isCaught());

        // Test when the thief is in the FOV
        Position thiefPosition = new Position(5, 5);
        LinkedList<Position> thiefHitbox = new LinkedList<>();
        thiefHitbox.add(thiefPosition);

        when(mockThief.getHitbox()).thenReturn(thiefHitbox);
        when(arena.fov(mockCop)).thenReturn(thiefHitbox);

        assertTrue(copController.isCaught());
    }


    @Test
    void testCaught() {

        copController.caught();
        verify(arena, atLeastOnce()).getGuards();
        verify(arena, atLeastOnce()).getCops();
        verify(arena, atLeastOnce()).getCamera();
        verify(arena, atLeastOnce()).getStillCameras();
    }


}
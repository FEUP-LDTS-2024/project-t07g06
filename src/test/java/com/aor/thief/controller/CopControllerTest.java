package com.aor.thief.controller.game;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



class CopControllerTest {
    private Arena arena;
    private CopController copController;
    private Cop moCop;
    private Thief moThief;


    @BeforeEach
    void setUp() {
        arena = mock(Arena.class);
        copController = new CopController(arena);
        moCop = mock(Cop.class);
        moThief = mock(Thief.class);
        LinkedList<Cop> cops = new LinkedList<>();
        cops.add(moCop);
        when(arena.getCops()).thenReturn(cops);
        when(arena.getThief()).thenReturn(moThief);
    }



    @Test
    void testIsCaught() {

        when(arena.fov(moCop)).thenReturn(new LinkedList<>());
        when(moThief.getHitbox()).thenReturn(new LinkedList<>());
        assertFalse(copController.isCaught());
        Position thiefPosition = new Position(5, 5);
        LinkedList<Position> thiefHitbox = new LinkedList<>();
        thiefHitbox.add(thiefPosition);
        when(moThief.getHitbox()).thenReturn(thiefHitbox);
        when(arena.fov(moCop)).thenReturn(thiefHitbox);
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
package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.states.GameOverState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
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
        Mockito.when(arena.getCops()).thenReturn(cops);
        Mockito.when(arena.getThief()).thenReturn(moThief);
    }

    @Test
    void isntCaught() {
        Mockito.when(arena.fov(moCop)).thenReturn(new LinkedList<>());
        Mockito.when(moThief.getHitbox()).thenReturn(new LinkedList<>());
        assertFalse(copController.isCaught());
    }

    @Test
    void isCaught() {
        Position thiefPosition = new Position(5, 5);
        LinkedList<Position> thiefHitbox = new LinkedList<>();
        thiefHitbox.add(thiefPosition);
        Mockito.when(moThief.getHitbox()).thenReturn(thiefHitbox);
        Mockito.when(arena.fov(moCop)).thenReturn(thiefHitbox);
        assertTrue(copController.isCaught());
    }

    @Test
    void testMovementLeft() {
        Position startPos = new Position(5, 5);
        Mockito.when(moCop.getPosition()).thenReturn(startPos);
        Mockito.when(moCop.getDirection()).thenReturn(1);
        Position newPos = copController.movement(startPos, 1, 0);
        assertEquals(new Position(4, 5), newPos);
    }

    @Test
    void testMovementRight() {
        Position startPos = new Position(5, 5);
        Mockito.when(moCop.getPosition()).thenReturn(startPos);
        Mockito.when(moCop.getDirection()).thenReturn(3);
        Position newPos = copController.movement(startPos, 3, 0);
        assertEquals(new Position(6, 5), newPos);
    }

    @Test
    void testPursuitMoveLeft() {
        Mockito.when(moThief.getPosition()).thenReturn(new Position(5, 5));
        Mockito.when(moCop.getPosition()).thenReturn(new Position(10, 5));
        copController.pursuit(moCop, moThief, 0);
        Mockito.verify(moCop).setDirection(1);
    }

    @Test
    void testPursuitMoveDown() {
        Mockito.when(moThief.getPosition()).thenReturn(new Position(5, 4));
        Mockito.when(moCop.getPosition()).thenReturn(new Position(5, 5));
        copController.pursuit(moCop, moThief, 0);
        assertEquals(0, moCop.getDirection());
    }
}

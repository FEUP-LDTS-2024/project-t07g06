package com.aor.thief.controller;

import com.aor.thief.Game;
import com.aor.thief.controller.game.GuardController;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.*;
import jdk.dynalink.linker.support.Guards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuardControllerTest {
    private GuardController controller;
    private Thief thief;
    private Arena arena;
    private Game game;

    @BeforeEach
    void setUp() {
        arena = new Arena(20, 20);

        thief = new Thief(6, 5, 0, 0, new Position(6, 5).updatehitbox_(), 0, "");
        arena.setThief(thief);

        arena.setWalls(Arrays.asList());
        arena.setGuards(Arrays.asList());

        controller = new GuardController(arena);

        game = Mockito.mock(Game.class);
    }

    @Test
    void moveGuardClosed() throws IOException {
        arena.setCops(new ArrayList<>());
        arena.setCameras(new ArrayList<>());
        arena.setStillCameras(new ArrayList<>());
        Guard guard = new Guard(10, 5, 1, false, new Position(10, 5).updatehitbox_(), 0, "");
        arena.setGuards(Arrays.asList(guard));
        arena.setWalls(Arrays.asList(
                new Wall(5, 5, new Position(10,5).updatehitbox_())));


            controller.step(game, GUI.ACTION.NONE, 750);


        assertEquals(new Position(10,5),guard.getPosition());
    }

    @Test
    void moveGuardsGap() throws IOException {
        Guard guard = new Guard(5, 5, 0, false, new Position(5, 5).updatehitbox_(), 0, "");
        arena.setGuards(Arrays.asList(guard));

        long time = 0;

        while (guard.getPosition().equals(new Position(5, 5))) {
            time += 500;
            controller.step(game, GUI.ACTION.NONE, time);
        }

        assertEquals(new Position(5, 6), guard.getPosition());
    }



    @Test
    void isThiefCaught() {
        Thief thief = new Thief(6, 5, 0, 0, new Position(6, 5).updatehitbox_(), 0, "");
        arena.setThief(thief);

        Guard guard = new Guard(5, 5, 0, false, new Position(5, 5).updatehitbox_(), 0, "");
        arena.setGuards(Arrays.asList(guard));

        Mockito.doNothing().when(game).playWantedSound();

        boolean caught = controller.isCaught();
        assertTrue(caught);
    }

    @Test
    void isntThiefCaught() {
        arena.setCops(new ArrayList<>());
        arena.setCameras(new ArrayList<>());
        arena.setStillCameras(new ArrayList<>());
        Thief thief = new Thief(5, 13, 0, 0, new Position(5, 13).updatehitbox_(), 0, "");
        arena.setThief(thief);

        arena.setWalls(Arrays.asList(
                new Wall(5, 10, new Position(5,10).updatehitbox_())));

        Guard guard = new Guard(5, 5, 0, false, new Position(5, 5).updatehitbox_(), 0, "");
        arena.setGuards(Arrays.asList(guard));


        boolean caught = controller.isCaught();
        assertFalse(caught);
    }


    @Test
    void multipleGuardsMoveIndependently() throws IOException {
        Guard guard1 = new Guard(5, 5, 0, false, new Position(5, 5).updatehitbox_(), 0, "");
        Guard guard2 = new Guard(7, 7, 0, false, new Position(7, 7).updatehitbox_(), 0, "");

        arena.setGuards(Arrays.asList(guard1, guard2));
        arena.setWalls(Collections.emptyList());

        controller.step(game, GUI.ACTION.NONE, 1000);

        assertNotEquals(new Position(5, 5), guard1.getPosition());
        assertNotEquals(new Position(7, 7), guard2.getPosition());
    }

    @Test
    void caughtStateUpdates() {
        Guard guard = new Guard(5, 5, 0, false, new Position(5, 5).updatehitbox_(), 0, "");
        Cop cop = new Cop(6, 6, 0, false, new Position(6, 6).updatehitbox_(), 0, "");
        Camera camera = new Camera(7, 7, 0, false, new Position(7, 7).updatehitbox_(), List.of(10), "");
        CameraStill stillCamera = new CameraStill(8, 8,0,false, new Position(8, 8).updatehitbox_(),List.of(10));

        arena.setGuards(Arrays.asList(guard));
        arena.setCops(Arrays.asList(cop));
        arena.setCameras(Arrays.asList(camera));
        arena.setStillCameras(Arrays.asList(stillCamera));

        controller.caught();

        assertTrue(guard.isFound());
        assertTrue(cop.isFound());
        assertTrue(camera.isFound());
        assertTrue(stillCamera.isFound());
    }



}


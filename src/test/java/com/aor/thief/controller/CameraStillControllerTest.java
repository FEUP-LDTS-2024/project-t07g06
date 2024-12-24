package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CameraStillControllerTest {

    private CameraStillController controller;
    private Arena arena;
    private Game game;
    private CameraStill cameraStill;

    @BeforeEach
    void setUp() {
        arena = new Arena(40,40);
        controller = new CameraStillController(arena);
        arena.setWalls(Arrays.asList(
                new Wall(10, 5, new Position(10,5).updatehitbox_())));
        game= Mockito.mock(Game.class);
        cameraStill=new CameraStill(10,10,0,false,new Position(10,10).updatehitbox_(),List.of(2));
        arena.setStillCameras(Arrays.asList(cameraStill));
    }

    @Test
    void isCaught() {
        Thief thief = new Thief(10,15,0,0,new Position(10,10).updatehitbox_(),0,"");
        arena.setThief(thief);
        boolean caught=controller.isCaught();
        assertTrue(caught);
    }

    @Test
    void isntCaught() {
        Thief thief=new Thief(10,40,0,0,new Position(10,40).updatehitbox_(),0,"");
        arena.setThief(thief);
        boolean caught=controller.isCaught();
        assertFalse(caught);
    }

    @Test
    void isntCaughtWall() {
        Thief thief=new Thief(10,17,0,0,new Position(10,17).updatehitbox_(),0,"");
        arena.setThief(thief);
        arena.setWalls(Arrays.asList(new Wall(10,15,new Position(10,15).updatehitbox_())));
        boolean caught=controller.isCaught();
        assertFalse(caught);
    }

    @Test
    void testStepWhenCaught() throws IOException {
        // Set up the scenario where the thief is caught by the camera
        CameraStill camera = new CameraStill(10, 10, 0, false, new Position(10, 10).updatehitbox_(), List.of(2));
        Thief thief = new Thief(10, 15, 0, 0, new Position(10, 15).updatehitbox_(), 0, "");

        arena.setStillCameras(Arrays.asList(camera));
        arena.setThief(thief);
        arena.setGuards(new ArrayList<>());
        arena.setCameras(new ArrayList<>());
        arena.setCops(new ArrayList<>());

        Game game = Mockito.mock(Game.class);


        controller.step(game, GUI.ACTION.NONE, 0);


        Mockito.verify(game).stopGameSound();
        Mockito.verify(game).playWantedSound();


        for (Guard guard : arena.getGuards()) {
            assertTrue(guard.isFound());
        }
        for (Cop cop : arena.getCops()) {
            assertTrue(cop.isFound());
        }
        for (Camera cameraInArena : arena.getCamera()) {
            assertTrue(cameraInArena.isFound());
        }
        for (CameraStill stillCamera : arena.getStillCameras()) {
            assertTrue(stillCamera.isFound());;
        }
    }

    @Test
    void testStepWhenNotCaught() throws IOException {

        CameraStill camera = new CameraStill(10, 10, 0, false, new Position(10, 10).updatehitbox_(), List.of(2));
        Thief thief = new Thief(10, 40, 0, 0, new Position(10, 40).updatehitbox_(), 0, "");

        arena.setStillCameras(Arrays.asList(camera));
        arena.setThief(thief);
        arena.setGuards(new ArrayList<>());
        arena.setCameras(new ArrayList<>());
        arena.setCops(new ArrayList<>());
        Game game = Mockito.mock(Game.class);


        controller.step(game, GUI.ACTION.NONE, 0);


        Mockito.verify(game, Mockito.never()).stopGameSound();
        Mockito.verify(game, Mockito.never()).playWantedSound();
        for (Guard guard : arena.getGuards()) {
            assertFalse(guard.isFound());
        }
        for (Cop cop : arena.getCops()) {
            assertFalse(cop.isFound());
        }
        for (Camera cameraInArena : arena.getCamera()) {
            assertFalse(cameraInArena.isFound());
        }
        for (CameraStill stillCamera : arena.getStillCameras()) {
            assertFalse(stillCamera.isFound());
        }
    }

}


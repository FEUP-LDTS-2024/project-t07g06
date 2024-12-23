package com.aor.thief.controller;

import com.aor.thief.Game;
import com.aor.thief.controller.game.CameraController;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CameraControllerTest {

    private CameraController controller;
    private Arena arena;
    private Game game;
    private Camera camera;

    @BeforeEach
    void setUp() {
        arena = new Arena(40, 40);
        controller = new CameraController(arena);
        game = Mockito.mock(Game.class);


        camera = new Camera(20, 20, 0, false, new Position(20, 20).updatehitbox_(), Arrays.asList(0,10,1),"");
        arena.setCameras(Arrays.asList(camera));
        arena.setWalls(Arrays.asList());
    }

    @Test
    void isCaughtDown() {

        Thief thief = new Thief(20, 29, 0, 0, new Position(20, 29).updatehitbox_(), 0, "");
        arena.setThief(thief);

        boolean caught = controller.isCaught();
        assertTrue(caught);
    }

    @Test
    void isCaughtLeft() throws IOException{
        arena.setGuards(new ArrayList<>());
        arena.setCops(new ArrayList<>());
        arena.setStillCameras(new ArrayList<>());

        Thief thief = new Thief(17, 19, 0, 0, new Position(17, 19).updatehitbox_(), 0, "");
        arena.setThief(thief);
        controller.step(game, GUI.ACTION.NONE, 1500);


        boolean caught = controller.isCaught();
        assertTrue(caught);
    }

    @Test
    void isntCaughtWasRight() throws IOException{
        Thief thief = new Thief(25, 20, 0, 0, new Position(25, 20).updatehitbox_(), 0, "");
        arena.setThief(thief);
        controller.step(game, GUI.ACTION.NONE, 1500);


        boolean caught = controller.isCaught();
        assertFalse(caught);
    }

    @Test
    void isntCaughtAfterRotation() throws IOException{
        arena.setGuards(new ArrayList<>());
        arena.setCops(new ArrayList<>());
        arena.setStillCameras(new ArrayList<>());
        Thief thief = new Thief(20, 30, 0, 0, new Position(20, 30).updatehitbox_(), 0, "");
        arena.setThief(thief);
        controller.step(game, GUI.ACTION.NONE, 1500);
        thief.setPosition(new Position(20,29));thief.setHitbox(new Position(20,29).updatehitbox_());
        boolean caught = controller.isCaught();
        assertFalse(caught);
    }

    @Test
    void rotateCamera() {

        int initialPos = camera.getCurrent_pos();
        controller.rotateCamera(0, initialPos);


        int newPos = camera.getCurrent_pos();
        assertNotEquals(initialPos, newPos);
    }


}
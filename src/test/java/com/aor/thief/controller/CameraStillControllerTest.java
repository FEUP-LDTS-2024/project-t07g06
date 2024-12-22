package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.CameraStill;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Cop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CameraStillControllerTest {

    private Arena arena;
    private CameraStillController controller;
    private Game game;

    @BeforeEach
    void setUp() {
        arena = mock(Arena.class);
        controller = new CameraStillController(arena);
        game = mock(Game.class);
    }

    @Test
    void testIsCaught_ThiefInCameraFoV() {
        // Arrange
        CameraStill camera = mock(CameraStill.class);
        Position thiefPosition = new Position(5, 5);
        LinkedList<Position> fov = new LinkedList<>();
        fov.add(thiefPosition);

        when(arena.getStillCameras()).thenReturn(new LinkedList<>() {{ add(camera); }});
        when(arena.fov(camera)).thenReturn(fov);
        when(arena.getThief().getHitbox()).thenReturn(new LinkedList<>() {{ add(thiefPosition); }});

        // Act
        boolean caught = controller.isCaught();

        // Assert
        assertTrue(caught);
    }

    @Test
    void testIsCaught_ThiefNotInCameraFoV() {
        // Arrange
        CameraStill camera = mock(CameraStill.class);
        LinkedList<Position> fov = new LinkedList<>();
        fov.add(new Position(3, 3));

        when(arena.getStillCameras()).thenReturn(new LinkedList<>() {{ add(camera); }});
        when(arena.fov(camera)).thenReturn(fov);
        when(arena.getThief().getHitbox()).thenReturn(new LinkedList<>() {{ add(new Position(5, 5)); }});

        // Act
        boolean caught = controller.isCaught();

        // Assert
        assertFalse(caught);
    }

    @Test
    void testStep_NoCameras() throws IOException {
        // Arrange
        when(arena.getStillCameras()).thenReturn(new LinkedList<>());

        // Act
        controller.step(game, GUI.ACTION.NONE, 0);

        // Assert
        verify(game, never()).stopGameSound();
        verify(game, never()).playWantedSound();
    }

    @Test
    void testStep_CameraFoundAndCaught() throws IOException {
        // Arrange
        CameraStill camera = mock(CameraStill.class);
        LinkedList<CameraStill> cameras = new LinkedList<>() {{ add(camera); }};

        when(arena.getStillCameras()).thenReturn(cameras);
        when(camera.isFound()).thenReturn(false);
        when(controller.isCaught()).thenReturn(true);
        when(arena.getGuards()).thenReturn(new LinkedList<>() {{ add(mock(Guard.class)); }});
        when(arena.getCops()).thenReturn(new LinkedList<>() {{ add(mock(Cop.class)); }});
        when(arena.getCamera()).thenReturn(new LinkedList<>());

        // Act
        controller.step(game, GUI.ACTION.NONE, 0);

        // Assert
        verify(game).stopGameSound();
        verify(game).playWantedSound();
        for (Guard guard : arena.getGuards()) {
            verify(guard).setFound(true);
        }
        for (Cop cop : arena.getCops()) {
            verify(cop).setFound(true);
        }
    }

    @Test
    void testStep_DefineFoVForCamera() throws IOException {
        // Arrange
        CameraStill camera = mock(CameraStill.class);

        when(camera.getCorner()).thenReturn(new LinkedList<>());
        when(arena.getStillCameras()).thenReturn(new LinkedList<>() {{ add(camera); }});

        // Act
        controller.step(game, GUI.ACTION.NONE, 0);

        // Assert
        verify(arena).defAxis(camera);
        verify(camera).setFov(any());
    }
}

package com.aor.thief.viewer;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.viewer.game.CameraViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraViewerTest {
    private GUI gui;
    private Camera camera;
    private CameraViewer cameraViewer;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(GUI.class);
        camera = Mockito.mock(Camera.class);
        cameraViewer = new CameraViewer();
    }

    @Test
    void drawEmptyDrawAndFound() throws IOException {
        Mockito.when(camera.getDraw()).thenReturn("");
        Mockito.when(camera.getCorner()).thenReturn(Arrays.asList(0, 10, 1));
        Mockito.when(camera.isFound()).thenReturn(true);
        Mockito.when(camera.getPosition()).thenReturn(new Position(1, 1));

        cameraViewer.draw(camera, gui);

        Mockito.verify(camera).setDraw("up_right_mid.png");
        Mockito.verify(gui).drawImage(new Position(1, 1), "src/main/resources/sprites/camera/");
        Mockito.verify(gui, Mockito.never()).drawImage(Mockito.any(Position.class), Mockito.eq("src/main/resources/sprites/vision/pixil-frame-0.png"));
    }

    @Test
    void drawNonEmptyDrawNotFound() throws IOException {
        Mockito.when(camera.getDraw()).thenReturn("down_left_mid.png");
        Mockito.when(camera.isFound()).thenReturn(false);
        Mockito.when(camera.getFov()).thenReturn(Arrays.asList(new Position(2, 2), new Position(3, 3)));
        Mockito.when(camera.getPosition()).thenReturn(new Position(1, 1));

        cameraViewer.draw(camera, gui);

        Mockito.verify(gui).drawImage(new Position(2, 2), "src/main/resources/sprites/vision/pixil-frame-0.png");
        Mockito.verify(gui).drawImage(new Position(3, 3), "src/main/resources/sprites/vision/pixil-frame-0.png");
        Mockito.verify(gui).drawImage(new Position(1, 1), "src/main/resources/sprites/camera/down_left_mid.png");
    }

    @Test
    void getCornerUpRight() {
        Mockito.when(camera.getCorner()).thenReturn(Arrays.asList(0, 10, 1));
        String result = cameraViewer.getCorner(camera);
        assertEquals("up_right", result);
    }

    @Test
    void getCornerUpLeft() {
        Mockito.when(camera.getCorner()).thenReturn(Arrays.asList(0, 30, 3));
        String result = cameraViewer.getCorner(camera);
        assertEquals("up_left", result);
    }

    @Test
    void getCornerDownLeft() {
        Mockito.when(camera.getCorner()).thenReturn(Arrays.asList(2, 32, 3));
        String result = cameraViewer.getCorner(camera);
        assertEquals("down_left", result);
    }

    @Test
    void getCornerDownRight() {
        Mockito.when(camera.getCorner()).thenReturn(Arrays.asList(2, 12, 1));
        String result = cameraViewer.getCorner(camera);
        assertEquals("down_right", result);
    }

    @Test
    void getCornerEmpty() {
        Mockito.when(camera.getCorner()).thenReturn(Arrays.asList(0, 0, 0));
        String result = cameraViewer.getCorner(camera);
        assertEquals("", result);
    }
}
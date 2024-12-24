package com.aor.thief.viewer;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.CameraStill;
import com.aor.thief.viewer.game.CameraStillViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

public class CameraStillViewerTest {
    private GUI gui;
    private CameraStill cameraStill;
    private CameraStillViewer cameraStillViewer;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(GUI.class);
        cameraStill = Mockito.mock(CameraStill.class);
        cameraStillViewer = new CameraStillViewer();
    }

    @Test
    void drawNotFoundWithVision() throws IOException {
        Mockito.when(cameraStill.isFound()).thenReturn(false);
        Mockito.when(cameraStill.getFov()).thenReturn(Arrays.asList(new Position(5, 5), new Position(6, 6)));
        Mockito.when(cameraStill.getDirection()).thenReturn(0);
        Mockito.when(cameraStill.getPosition()).thenReturn(new Position(1, 1));

        cameraStillViewer.draw(cameraStill, gui);

        Mockito.verify(gui).drawImage(new Position(5, 5), "src/main/resources/sprites/vision/pixil-frame-0.png");
        Mockito.verify(gui).drawImage(new Position(6, 6), "src/main/resources/sprites/vision/pixil-frame-0.png");
        Mockito.verify(gui).drawImage(new Position(1, 1), "src/main/resources/sprites/camera/up_left_down.png");
    }

    @Test
    void drawFound() throws IOException {
        Mockito.when(cameraStill.isFound()).thenReturn(true);
        Mockito.when(cameraStill.getDirection()).thenReturn(1);
        Mockito.when(cameraStill.getPosition()).thenReturn(new Position(1, 1));

        cameraStillViewer.draw(cameraStill, gui);

        Mockito.verify(gui, Mockito.never()).drawImage(Mockito.any(Position.class), Mockito.eq("src/main/resources/sprites/vision/pixil-frame-0.png"));
        Mockito.verify(gui).drawImage(new Position(1, 1), "src/main/resources/sprites/camera/up_right_up.png");
    }

    @Test
    void drawDirectionDownRight() throws IOException {
        Mockito.when(cameraStill.isFound()).thenReturn(true);
        Mockito.when(cameraStill.getDirection()).thenReturn(2);
        Mockito.when(cameraStill.getPosition()).thenReturn(new Position(1, 1));

        cameraStillViewer.draw(cameraStill, gui);

        Mockito.verify(gui).drawImage(new Position(1, 1), "src/main/resources/sprites/camera/down_right_down.png");
    }

    @Test
    void drawDirectionUpLeft() throws IOException {
        Mockito.when(cameraStill.isFound()).thenReturn(true);
        Mockito.when(cameraStill.getDirection()).thenReturn(3);
        Mockito.when(cameraStill.getPosition()).thenReturn(new Position(1, 1));

        cameraStillViewer.draw(cameraStill, gui);

        Mockito.verify(gui).drawImage(new Position(1, 1), "src/main/resources/sprites/camera/up_left_up.png");
    }
}

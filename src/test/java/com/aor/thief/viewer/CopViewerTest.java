package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Cop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CopViewerTest {

    private GUI gui;
    private Cop cop;
    private CopViewer viewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        cop = mock(Cop.class);
        viewer = new CopViewer();
    }

    @Test
    void testDraw_CopFound() throws IOException, InterruptedException {
        // Arrange
        when(cop.isFound()).thenReturn(true);
        when(cop.getPosition()).thenReturn(new Position(5, 5));
        when(cop.getDraw()).thenReturn("still_down1.png");


        viewer.draw(cop, gui);


        verify(gui).drawImage(cop.getPosition(), "src/main/resources/sprites/cop/still_down1.png");
        verify(gui, never()).drawImage(any(), eq("src/main/resources/sprites/vision/pixil-frame-0.png"));
    }

    @Test
    void testDraw_CopNotFound() throws IOException, InterruptedException {

        LinkedList<Position> fov = new LinkedList<>();
        fov.add(new Position(4, 5));
        fov.add(new Position(5, 4));

        when(cop.isFound()).thenReturn(false);
        when(cop.getFov()).thenReturn(fov);
        when(cop.getPosition()).thenReturn(new Position(5, 5));
        when(cop.getDraw()).thenReturn("still_down1.png");


        viewer.draw(cop, gui);


        verify(gui).drawImage(new Position(4, 5), "src/main/resources/sprites/vision/pixil-frame-0.png");
        verify(gui).drawImage(new Position(5, 4), "src/main/resources/sprites/vision/pixil-frame-0.png");
        verify(gui).drawImage(cop.getPosition(), "src/main/resources/sprites/cop/still_down1.png");
    }

    @Test
    void testGetDirection() {

        when(cop.getDirection()).thenReturn(0, 1, 2, 3, -1);


        assertEquals("down1", viewer.getDirection(cop));
        assertEquals("left1", viewer.getDirection(cop));
        assertEquals("up1", viewer.getDirection(cop));
        assertEquals("right1", viewer.getDirection(cop));
        assertEquals("down1", viewer.getDirection(cop)); // Default case
    }
}

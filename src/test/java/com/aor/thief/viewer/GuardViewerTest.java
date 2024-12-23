package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Guard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GuardViewerTest {

    private GUI gui;
    private Guard guard;
    private GuardViewer viewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        guard = mock(Guard.class);
        viewer = new GuardViewer();
    }

    @Test
    void testDraw_GuardFound() throws IOException, InterruptedException {

        when(guard.isFound()).thenReturn(true);
        when(guard.getPosition()).thenReturn(new Position(5, 5));
        when(guard.getDraw()).thenReturn("still_down1.png");


        viewer.draw(guard, gui);


        verify(gui).drawImage(guard.getPosition(), "src/main/resources/sprites/guards/still_down1.png");
        verify(gui, never()).drawImage(any(), eq("src/main/resources/sprites/vision/pixil-frame-0.png"));
    }

    @Test
    void testDraw_GuardNotFound() throws IOException, InterruptedException {

        LinkedList<Position> fov = new LinkedList<>();
        fov.add(new Position(4, 5));
        fov.add(new Position(5, 4));

        when(guard.isFound()).thenReturn(false);
        when(guard.getFov()).thenReturn(fov);
        when(guard.getPosition()).thenReturn(new Position(5, 5));
        when(guard.getDraw()).thenReturn("still_down1.png");


        viewer.draw(guard, gui);


        verify(gui).drawImage(new Position(4, 5), "src/main/resources/sprites/vision/pixil-frame-0.png");
        verify(gui).drawImage(new Position(5, 4), "src/main/resources/sprites/vision/pixil-frame-0.png");
        verify(gui).drawImage(guard.getPosition(), "src/main/resources/sprites/guards/still_down1.png");
    }

    @Test
    void testGetDirection() {

        when(guard.getDirection()).thenReturn(0, 1, 2, 3, -1);


        assertEquals("down1", viewer.getDirection(guard));
        assertEquals("left1", viewer.getDirection(guard));
        assertEquals("up1", viewer.getDirection(guard));
        assertEquals("right1", viewer.getDirection(guard));
        assertEquals("down1", viewer.getDirection(guard)); // Default case
    }
}

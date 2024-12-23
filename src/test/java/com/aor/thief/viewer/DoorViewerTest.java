package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Door;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.mockito.Mockito.*;

class DoorViewerTest {

    private GUI gui;
    private Door door;
    private DoorViewer viewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        door = mock(Door.class);
        viewer = new DoorViewer();
    }

    @Test
    void testDraw_DoorClosed() throws IOException {

        when(door.getPosition()).thenReturn(new Position(3, 3));

        viewer.draw(door, gui);

        verify(gui).drawImage(new Position(3, 3), "src/main/resources/sprites/door/closed.png");
    }
}

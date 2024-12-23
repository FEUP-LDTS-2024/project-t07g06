package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.DeadBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class DeadBodyViewerTest {

    private GUI gui;
    private DeadBody deadBody;
    private DeadBodyViewer viewer;

    @BeforeEach
    void setUp() {

        gui = mock(GUI.class);
        deadBody = mock(DeadBody.class);
        viewer = new DeadBodyViewer();
    }


    @Test
    void testDraw_GuardDeadBody() throws IOException {

        when(deadBody.getType()).thenReturn("G");
        when(deadBody.getPosition()).thenReturn(new Position(5, 5));
        viewer.draw(deadBody, gui);
        verify(gui).drawImage(new Position(5, 5), "src/main/resources/sprites/guards/dead.png");
    }

    @Test
    void testDraw_CopDeadBody() throws IOException {

        when(deadBody.getType()).thenReturn("C");
        when(deadBody.getPosition()).thenReturn(new Position(10, 10));
        viewer.draw(deadBody, gui);
        verify(gui).drawImage(new Position(10, 10), "src/main/resources/sprites/cop/dead.png");
    }
}

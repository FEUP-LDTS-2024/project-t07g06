
package com.aor.thief.viewer;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Wall;
import com.aor.thief.viewer.game.WallViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;

public class WallViewerTest {

    private WallViewer wallViewer;
    private Wall wall;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        wallViewer = new WallViewer();
        wall = Mockito.mock(Wall.class);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDraw() throws IOException {

        Mockito.when(wall.getPosition()).thenReturn(new Position(5, 5));


        wallViewer.draw(wall, gui);


        verify(gui).drawImage(new Position(5, 5), "src/main/resources/sprites/wall/wall8.png");
    }
}
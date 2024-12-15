package com.aor.thief.viewer;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.*;
import com.aor.thief.viewer.game.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

public class ArenaViewerTest{
    private GUI gui;
    private GameViewer viewer;
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10);
        gui = Mockito.mock(GUI.class);
        viewer = new GameViewer(arena);

        arena.setWalls(Arrays.asList(new Wall(1, 2), new Wall(2, 3), new Wall(3, 4)));
        arena.setGuards(Arrays.asList(new Guard(4, 5, 0), new Guard(5, 6, 0)));
        arena.setPrize(Arrays.asList(new Prize(7, 7)));
        arena.setThief(new Thief(5, 8, 0, 0,0, ""));
        arena.setDoor(new Door(6,8));
    }


    @Test
    void drawWalls() throws IOException, InterruptedException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(1, 2));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(2, 3));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(3, 4));
        Mockito.verify(gui, Mockito.times(3)).drawWall(Mockito.any(Position.class));
    }

    @Test
    void drawGuards() throws IOException, InterruptedException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawGuard(new Position(4, 5));
        Mockito.verify(gui, Mockito.times(1)).drawGuard(new Position(5, 6));
        Mockito.verify(gui, Mockito.times(2)).drawGuard(Mockito.any(Position.class));
    }

    @Test
    void drawThief() throws IOException, InterruptedException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawThief(new Position(5, 8));
        Mockito.verify(gui, Mockito.times(1)).drawThief(Mockito.any(Position.class));
    }

    @Test
    void drawDoor() throws IOException, InterruptedException {
        arena.setPrize(Arrays.asList());
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawDoor(new Position(6, 8));
        Mockito.verify(gui, Mockito.times(1)).drawThief(Mockito.any(Position.class));
    }

    @Test
    void refreshAndClear() throws IOException, InterruptedException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }

}

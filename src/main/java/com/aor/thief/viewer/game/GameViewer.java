package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Element;
import com.aor.thief.model.Position;
import com.aor.thief.viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {
    public GameViewer(Arena arena) {
        super(arena);
    }

    @Override
    public void drawElements(GUI gui) throws InterruptedException{
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElements(gui, getModel().getGuards(), new GuardViewer());
        drawElement(gui, getModel().getThief(), new ThiefViewer());
        drawElements(gui, getModel().getPrize(), new PrizeViewer());
        if(getModel().getPrize().isEmpty())drawElement(gui, getModel().getDoor(), new DoorViewer());
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) throws  InterruptedException {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) throws InterruptedException {
        viewer.draw(element, gui);
    }
}

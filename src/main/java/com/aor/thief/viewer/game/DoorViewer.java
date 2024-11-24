package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Door;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.viewer.Viewer;

public class DoorViewer implements ElementViewer<Door> {
    @Override
    public void draw(Door door, GUI gui) {
        gui.drawDoor(door.getPosition());
    }
}

package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Door;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.viewer.Viewer;

import java.io.IOException;

public class DoorViewer implements ElementViewer<Door> {
    private final String path = "src/main/resources/sprites/door/";
    @Override
    public void draw(Door door, GUI gui) throws IOException {
        //gui.drawImage(door.getPosition(),"");
        gui.drawImage(door.getPosition(), path+"door.png");
    }
}

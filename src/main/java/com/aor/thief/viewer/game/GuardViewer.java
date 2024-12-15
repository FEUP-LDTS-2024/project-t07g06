package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Wall;

import java.io.IOException;

public class GuardViewer implements ElementViewer<Guard> {
    private final String path = "src/main/resources/sprites/guards/";
    @Override
    public void draw(Guard guard, GUI gui) throws IOException {
        if(guard.getDraw().isEmpty()){
            gui.drawImage(guard.getPosition(), path + "still_down.png");}
        else
            gui.drawImage(guard.getPosition(), path + guard.getDraw());
    }
}

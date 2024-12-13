package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Wall;

import java.io.IOException;

public class GuardViewer implements ElementViewer<Guard> {
    private final String path = "src/main/resources/sprites/thief/";
    @Override
    public void draw(Guard guard, GUI gui) throws IOException {
        switch (guard.getDirection()) {
            case (0):
                gui.drawImage(guard.getPosition(), path+"rightleg_down.png");
                break;
            case (1):
                gui.drawImage(guard.getPosition(), path+"rightleg_left.png");
                break;
            case (2):
                gui.drawImage(guard.getPosition(), path+"rightleg_up.png");
                break;
            case (3):
                gui.drawImage(guard.getPosition(), path+"rightleg_up.png");
                break;
        }
    }
}

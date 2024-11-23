package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Wall;

public class GuardViewer implements ElementViewer<Guard> {
    @Override
    public void draw(Guard guard, GUI gui) {
        gui.drawGuard(guard.getPosition());
    }
}

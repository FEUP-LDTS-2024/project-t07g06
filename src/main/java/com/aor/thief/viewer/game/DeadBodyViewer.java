package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.DeadBody;
import com.aor.thief.model.game.elements.Door;

public class DeadBodyViewer implements ElementViewer<DeadBody>{
    @Override
    public void draw(DeadBody deadBody, GUI gui) {
        gui.drawDeadBody(deadBody.getPosition());
    }
}

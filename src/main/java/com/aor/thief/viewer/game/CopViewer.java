package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.DeadBody;

public class CopViewer implements ElementViewer<Cop> {
    @Override
    public void draw(Cop cop, GUI gui) {
        gui.drawCop(cop.getPosition());
    }
}

package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;

public class ThiefViewer implements ElementViewer<Thief> {
    @Override
    public void draw(Thief thief, GUI gui) {
        gui.drawImage(thief.getPosition(),"");
    }
}

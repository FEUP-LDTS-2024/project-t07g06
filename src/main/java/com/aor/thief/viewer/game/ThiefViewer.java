package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;

public class ThiefViewer implements ElementViewer<Thief> {
    @Override
    public void draw(Thief thief, GUI gui) {
        switch (thief.getDirection()){
            case(0):
                gui.drawImage(thief.getPosition(),"down");
                break;
            case(1):
                gui.drawImage(thief.getPosition(),"left");
                break;
            case(2):
                gui.drawImage(thief.getPosition(),"up");
                break;
            case(3):
                gui.drawImage(thief.getPosition(),"right");
                break;
        }
    }
}

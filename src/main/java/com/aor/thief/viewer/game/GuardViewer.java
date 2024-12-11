package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Wall;

public class GuardViewer implements ElementViewer<Guard> {
    @Override
    public void draw(Guard guard, GUI gui) {

        switch (guard.getDirection()){
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

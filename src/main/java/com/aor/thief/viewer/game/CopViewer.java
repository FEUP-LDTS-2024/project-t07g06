package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.DeadBody;
import com.aor.thief.model.game.elements.Guard;

import java.io.IOException;

public class CopViewer implements ElementViewer<Cop> {
    private final String path = "src/main/resources/sprites/cop/";
    @Override
    public void draw(Cop cop, GUI gui) throws IOException, InterruptedException {
        if(cop.getDraw().isEmpty()){
            cop.setDraw("still_" + getDirection(cop) + ".png");}
        gui.drawImage(cop.getPosition(), path + cop.getDraw());
    }

    public String getDirection(Cop cop) {
        switch (cop.getDirection()) {
            case (0):
                return "down";
            case (1):
                return "left";
            case (2):
                return "up";
            case (3):
                return "right";
            default:
                return "down";
        }
    }
}

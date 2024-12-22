package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.googlecode.lanterna.TextColor;
import com.aor.thief.model.Position;
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
        if(!cop.isFound()) {
            for (Position position : cop.getFov()) {
                gui.drawImage(position, "src/main/resources/sprites/vision/pixil-frame-0.png");
            }
        }
        gui.drawImage(cop.getPosition(), path + cop.getDraw());
    }

    public String getDirection(Cop cop) {
        switch (cop.getDirection()) {
            case (0):
                return "down1";
            case (1):
                return "left1";
            case (2):
                return "up1";
            case (3):
                return "right1";
            default:
                return "down1";
        }
    }
}

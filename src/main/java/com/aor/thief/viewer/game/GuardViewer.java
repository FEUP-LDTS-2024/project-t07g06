package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Guard;

import java.io.IOException;

public class GuardViewer implements ElementViewer<Guard> {
    private final String path = "src/main/resources/sprites/guards/";
    @Override
    public void draw(Guard guard, GUI gui) throws IOException, InterruptedException {
        if(guard.getDraw().isEmpty()){
            guard.setDraw("still_" + getDirection(guard) + ".png");}
        if(!guard.isFound()) {
            for (Position position : guard.getFov()) {
                gui.drawImage(position, "src/main/resources/sprites/vision/pixil-frame-0.png");
            }
        }
        gui.drawImage(guard.getPosition(), path + guard.getDraw());
    }


    public String getDirection(Guard guard) {
        switch (guard.getDirection()) {
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

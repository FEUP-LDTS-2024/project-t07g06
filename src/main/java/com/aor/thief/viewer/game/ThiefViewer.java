package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThiefViewer implements ElementViewer<Thief> {
    private String path = "src/main/resources/sprites/guard/";
   // Time when the current action started
    @Override
    public void draw(Thief thief, GUI gui) throws InterruptedException, IOException {
        if(thief.getDraw().isEmpty()) {
            thief.setDraw("still_" +getDirection(thief) + ".png");
        }
        System.out.println(path+thief.getDraw());
        gui.drawImage(thief.getPosition(), path+thief.getDraw());

    }

    public String getDirection(Thief thief) {
        switch (thief.getDirection()) {
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

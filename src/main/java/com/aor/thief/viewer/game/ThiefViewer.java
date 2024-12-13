package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThiefViewer implements ElementViewer<Thief> {
    private final String path = "src/main/resources/sprites/thief/";
   // Time when the current action started
    @Override
    public void draw(Thief thief, GUI gui) throws InterruptedException, IOException {
        String action = getAction(gui,thief);
        String direction = getDirection(thief.getDirection());
        if(action.equals("rightleg_")||action.equals("leftleg_")) {
            gui.drawImage(thief.getPosition(), path + action + direction + ".png");
            Thread.sleep(25);
        }else{
            gui.drawImage(thief.getPosition(), path + action + direction + ".png");
            Thread.sleep(100);
            action ="still_";
        }

    }
    private String getAction(GUI gui,Thief thief) throws IOException, InterruptedException {
        switch (gui.getNextAction()) {
            case GUI.ACTION.KILL:
                return "shoot_";
            default:
                String leg = (thief.getFeet() == 0) ? "rightleg_" : "leftleg_";
                return leg;
        }
    }

    private String getDirection(int direction) {
        switch (direction) {
            case 0: return "down";
            case 1: return "left";
            case 2: return "up";
            case 3: return "right";
            default: return "down";
        }
    }
}

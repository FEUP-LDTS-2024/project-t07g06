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
            System.out.println(path + action + direction + ".png");
            gui.drawImage(thief.getPosition(), path + action + direction + ".png");
        }else{
            System.out.println(path + action + direction + ".png");
            gui.drawImage(thief.getPosition(), path + action + direction + ".png");
            Thread.sleep(50);
            action ="still_";
            System.out.println(path + action + direction + ".png");
            gui.drawImage(thief.getPosition(), path + action + direction + ".png");
        }

    }
    private String getAction(GUI gui,Thief thief) throws IOException, InterruptedException {
        switch (gui.getNextAction()) {
            case GUI.ACTION.KILL:
                return "shoot_";
            default:
                // Use leftleg or rightleg based on thief's foot state
                String leg = (thief.getFeet() == 0) ? "rightleg_" : "leftleg_";
                return leg;
        }
    }

    private String getDirection(int direction) {
        // Return the appropriate direction string based on the thief's direction
        switch (direction) {
            case 0: return "down";  // Down
            case 1: return "left";  // Left
            case 2: return "up";    // Up
            case 3: return "right"; // Right
            default: return "down"; // Default to down if something goes wrong
        }
    }
}

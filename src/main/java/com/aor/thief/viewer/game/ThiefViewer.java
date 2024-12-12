package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;

import java.io.IOException;

public class ThiefViewer implements ElementViewer<Thief> {
    //tentar resolver, não sei se vai, se calhar meter o pé onde se chama o thiefviewer
    @Override
    public void draw(Thief thief, GUI gui) throws InterruptedException, IOException {
        String path = "src/main/resources/sprites/thief/";
        //de momento sempre que se chamar ele vai desenhar o caso 0 porque isto aqui sempre que
        if(gui.getNextAction() != GUI.ACTION.NONE || gui.getNextAction() != GUI.ACTION.KILL) Thread.sleep(75);
        switch (gui.getNextAction()){
            case GUI.ACTION.KILL:
                path += "shoot_";
                break;
            case GUI.ACTION.NONE:
                path += "still_";
                break;
            default:
                path += (thief.getFeet() == 0) ? "rightleg_" : "leftleg_";
        }
        switch (thief.getDirection()) {
            case 0:
                gui.drawImage(thief.getPosition(), path + "down.png");
                break;
            case 1:
                gui.drawImage(thief.getPosition(), path + "left.png");
                break;
            case 2:
                gui.drawImage(thief.getPosition(), path + "up.png");
                break;
            case 3:
                gui.drawImage(thief.getPosition(), path + "right.png");
                break;
        }

    }
}

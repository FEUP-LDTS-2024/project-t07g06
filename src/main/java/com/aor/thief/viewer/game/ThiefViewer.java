package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;

import java.io.IOException;

public class ThiefViewer implements ElementViewer<Thief> {
    private int feet=0; //tentar resolver, não sei se vai, se calhar meter o pé onde se chama o thiefviewer
    @Override
    public void draw(Thief thief, GUI gui) throws InterruptedException, IOException {
        String path = "src/main/resources/sprites/thief/";
        //de momento sempre que se chamar ele vai desenhar o caso 0 porque isto aqui sempre que
        if (gui.getNextAction() == GUI.ACTION.KILL) {
            path += "shoot_";
        } else if (gui.getNextAction() == GUI.ACTION.NONE) {
            path += "still_";
        } else {
            path += (feet == 0) ? "rightleg_" : "leftleg_";
            feet = (feet + 1) % 2;
        }
        switch (thief.getDirection()) {
            case 0:
                path += "down.png";
                break;
            case 1:
                path += "left.png";
                break;
            case 2:
                path += "up.png";
                break;
            case 3:
                path += "right.png";
                break;
        }
        gui.drawImage(thief.getPosition(), path);
    }
}

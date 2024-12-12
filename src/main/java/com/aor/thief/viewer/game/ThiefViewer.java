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
        switch (gui.getNextAction()){
            case GUI.ACTION.KILL:
                path += "shoot_";
                break;
            case GUI.ACTION.NONE:
                path += "still_";
                break;
            default:
                path += (thief.getFeet() == 0) ? "rightleg_" : "leftleg_";
                System.out.println(thief.getFeet());
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

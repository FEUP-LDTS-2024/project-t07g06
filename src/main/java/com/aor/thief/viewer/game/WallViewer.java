package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Wall;

import java.io.IOException;

public class WallViewer implements ElementViewer<Wall> {
    private final String path = "src/main/resources/sprites/thief/";
    @Override
    public void draw(Wall wall, GUI gui) throws IOException {
      //  gui.drawImage(wall.getPosition(),"");
        gui.drawImage(wall.getPosition(), path+"still_down.png");
    }

}

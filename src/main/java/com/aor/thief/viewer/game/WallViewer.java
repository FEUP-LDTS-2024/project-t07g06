package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Wall;

public class WallViewer implements ElementViewer<Wall> {
    @Override
    public void draw(Wall wall, GUI gui) {
      //  gui.drawImage(wall.getPosition(),"");
        gui.drawWall(wall.getPosition());
    }

}

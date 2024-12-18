package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.DeadBody;
import com.aor.thief.model.game.elements.Door;

import java.io.IOException;

public class DeadBodyViewer implements ElementViewer<DeadBody>{
    private final String path = "src/main/resources/sprites/";
    @Override
    public void draw(DeadBody deadBody, GUI gui) throws IOException {
        if(deadBody.getType().equals("G")) gui.drawImage(deadBody.getPosition(),path+"guards/dead.png");
        else gui.drawImage(deadBody.getPosition(),path+"cop/dead.png");
    }
}

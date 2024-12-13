package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Prize;
import com.aor.thief.model.game.elements.Wall;

import java.io.IOException;

public class PrizeViewer implements ElementViewer<Prize>{
    private final String path = "src/main/resources/sprites/thief/";
    @Override
    public void draw(Prize prize, GUI gui) throws IOException {
       // gui.drawImage(prize.getPosition(),"");
        gui.drawImage(prize.getPosition(), path+"still_down.png");
    }
}

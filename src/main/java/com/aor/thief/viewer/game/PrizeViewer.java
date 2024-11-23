package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Prize;
import com.aor.thief.model.game.elements.Wall;

public class PrizeViewer implements ElementViewer<Prize>{
    @Override
    public void draw(Prize prize, GUI gui) {
        gui.drawPrize(prize.getPosition());
    }
}

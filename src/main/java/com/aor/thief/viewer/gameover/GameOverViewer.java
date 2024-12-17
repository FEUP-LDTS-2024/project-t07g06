package com.aor.thief.viewer.gameover;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.gameover.GameOver;
import com.aor.thief.viewer.Viewer;

public class GameOverViewer extends Viewer<GameOver> {

    public GameOverViewer(GameOver gameOver) {super(gameOver);}

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(5, 5), "GAME OVER", "#FFFFFF");

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(5 , 7+i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}

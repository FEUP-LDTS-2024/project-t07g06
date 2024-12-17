package com.aor.thief.viewer.win;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;

import com.aor.thief.model.win.Win;
import com.aor.thief.viewer.Viewer;

public class WinViewer extends Viewer<Win> {

    public WinViewer(Win win) {super(win);}

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


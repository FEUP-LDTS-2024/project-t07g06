package com.aor.thief.viewer.instructions;

import com.aor.thief.controller.instructions.InstructionsController;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.gameover.GameOver;
import com.aor.thief.model.instructions.Instructions;
import com.aor.thief.viewer.Viewer;

public class InstructionsViewer extends Viewer<Instructions>{

    public InstructionsViewer(Instructions instructions) {super(instructions);}

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(5, 5), "INSTRUCTIONS", "#FFFFFF");
        gui.drawText(new Position(5, 6), "to move use: awsd or the arrows", "#FFFFFF");
        gui.drawText(new Position(5, 7), "to shoot use: space", "#FFFFFF");
        gui.drawText(new Position(5, 8), "to leave the room use: E", "#FFFFFF");


        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(5 , 10+i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}


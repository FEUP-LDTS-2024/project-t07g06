package com.aor.thief.gui;

import com.aor.thief.model.Position;

import java.io.IOException;
import java.util.List;

public interface GUI {
    ACTION getNextAction() throws IOException, InterruptedException;

    void drawThief(Position position);

    void drawWall(Position position);


    void drawGuard(Position position);

    void drawDoor(Position position);

    void drawImage(Position positions,String filename) throws IOException;

    void  drawCharacter(int x, int y, char c, String color);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, ESCAPE, PICK_UP, KILL}
}

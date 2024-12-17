package com.aor.thief.gui;

import com.aor.thief.model.Position;

import java.io.IOException;
import java.util.List;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawThief(Position position);

    void drawPrize(Position position);

    void drawWall(Position position);

    void drawDeadBody(Position position);

    void drawGuard(Position position);

    void drawDoor(Position position);


    void drawCop(Position position);

    void drawCamera(Position position);



    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;


    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, ESCAPE,KILL}
}

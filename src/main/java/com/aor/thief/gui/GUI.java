package com.aor.thief.gui;

import com.aor.thief.model.Position;

import java.io.IOException;

public interface GUI {
    void drawThief(Position position);

    void drawPrize(Position position);

    void drawWall(Position position);

    void drawGuard(Position position);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;
}

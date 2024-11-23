package com.aor.thief.gui;

import com.aor.thief.model.game.menu.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class LanternaGUI implements GUI {
    private final Screen screen;
    private final Terminal terminal;


    public LanternaGUI(Screen screen, Terminal terminal) {
        this.screen = screen;
        this.terminal = terminal;
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary();
    }

    @Override
    public void drawThief(Position position) {
        drawCharacter(position.getX(), position.getY(), 'T', "#FFD700");
    }

    @Override
    public void drawPrize(Position position) {
        drawCharacter(position.getX(), position.getY(), '£', "#FFD700");
    }

    @Override
    public void drawWall(Position position) {
        drawCharacter(position.getX(), position.getY(), '#', "#3333FF");
    }

    @Override
    public void drawGuard(Position position) {
        drawCharacter(position.getX(), position.getY(), '@', "#CC0000");
    }

    @Override
    public void drawText(Position position, String text, String color) {

    }
    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y + 1, "" + c);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}

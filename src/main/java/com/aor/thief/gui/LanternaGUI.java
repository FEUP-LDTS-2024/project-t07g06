package com.aor.thief.gui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class LanternaGUI {
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
    public void draw() throws IOException {
        screen.clear();
        screen.refresh();

    }
}

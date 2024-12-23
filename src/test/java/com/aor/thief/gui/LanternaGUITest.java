package com.aor.thief.gui;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Thief;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        gui = new LanternaGUI(screen);
    }

    @Test
    void drawThief() {
        gui.drawThief(new Position(1, 1));

        verify(tg, times(1)).setForegroundColor(new TextColor.RGB(255, 215, 0));
        verify(tg, times(1)).putString(1, 2, "T");
    }

    @Test
    void drawText() {
        gui.drawText(new Position(1, 1), "Hello World", "#336699");

        verify(tg, times(1)).setForegroundColor(TextColor.Factory.fromString("#336699"));
        verify(tg, times(1)).putString(1, 1, "Hello World");
    }

    @Test
    void drawImageThrowsIOExceptionForInvalidPath() {
        Position position = new Position(1, 1);
        String invalidPath = "invalid/path/to/image.png";

        assertThrows(IOException.class, () -> gui.drawImage(position, invalidPath));
    }

    @Test
    void getNextActionArrowKeys() throws IOException, InterruptedException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowUp));

        GUI.ACTION action = gui.getNextAction();

        assertEquals(GUI.ACTION.UP, action);
    }

    @Test
    void getNextActionCharacterKey() throws IOException, InterruptedException {
        when(screen.pollInput()).thenReturn(new KeyStroke('q', false, false));

        GUI.ACTION action = gui.getNextAction();

        assertEquals(GUI.ACTION.QUIT, action);
    }

    @Test
    void drawWall() {
        gui.drawWall(new Position(5, 5));

        verify(tg, times(1)).setForegroundColor(new TextColor.RGB(51, 51, 255));
        verify(tg, times(1)).putString(5, 6, "#");
    }


    @Test
    void clearScreen() {
        gui.clear();

        verify(screen, times(1)).clear();
    }

    @Test
    void refreshScreen() throws IOException {
        gui.refresh();

        verify(screen, times(1)).refresh();
    }

    @Test
    void closeScreen() throws IOException {
        gui.close();

        verify(screen, times(1)).close();
    }
}

package com.aor.thief;
import com.aor.thief.gui.LanternaGUI;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.arena.LoaderArenaBuilder;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.GameState;
import com.aor.thief.states.MenuState;
import com.aor.thief.states.State;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(35, 20);
        this.state = new MenuState(new Menu()); //objetivo:Aqui ler o menu, para depois o menu lançar o nivel
    }
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Game().start();

    }

    public void setState(State state) {
        this.state = state;
    }

    private void start() throws IOException {
        int FPS = 30;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
        gui.close();
    }
}


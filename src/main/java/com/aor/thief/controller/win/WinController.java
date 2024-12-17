package com.aor.thief.controller.win;

import com.aor.thief.Game;
import com.aor.thief.controller.Controller;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.arena.LoaderArenaBuilder;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.model.win.Win;
import com.aor.thief.states.GameState;
import com.aor.thief.states.MenuState;

import java.io.IOException;

public class WinController extends Controller<Win> {
    public WinController(Win win) {super(win);}

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action){
            case UP:
                getModel().previousEntry();
                game.playMenuChange();
                break;
            case DOWN:
                getModel().nextEntry();
                game.playMenuChange();
                break;
            case SELECT:
                game.stopWin();
                game.playGameSound();
                if (getModel().isSelectedExit()) {game.playMenuSelect(); game.setState(new MenuState(new Menu()));}
                if (getModel().isSelectedStart()){game.playMenuSelect(); game.setState(new GameState(new LoaderArenaBuilder(1).createArena()));}
        }
    }
}







package com.aor.thief.controller.menu;


import com.aor.thief.Game;
import com.aor.thief.controller.Controller;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.arena.LoaderArenaBuilder;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.GameState;


import java.io.IOException;


public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu){
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action){
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(null);
                if (getModel().isSelectedStart()) game.setState(new GameState(new LoaderArenaBuilder(1).createArena()));
        }
    }
}

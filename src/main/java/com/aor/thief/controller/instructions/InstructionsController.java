package com.aor.thief.controller.instructions;

import com.aor.thief.Game;
import com.aor.thief.controller.Controller;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.arena.LoaderArenaBuilder;
import com.aor.thief.model.instructions.Instructions;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.GameState;
import com.aor.thief.states.MenuState;

import java.io.IOException;

public class InstructionsController  extends Controller<Instructions> {



public InstructionsController(Instructions instructions) {super(instructions);}

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
            if (getModel().isSelectedExit()) {game.playMenuSelect(); game.setState(new MenuState(new Menu()));}
            if (getModel().isSelectedStart()){game.playMenuSelect(); game.setState(new GameState(new LoaderArenaBuilder(1).createArena()));}
    }
}
}

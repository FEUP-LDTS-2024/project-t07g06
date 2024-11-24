package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Guard;

import java.io.IOException;

public class GuardController extends GameController{
    private long lastMovement;

    public GuardController(Arena arena){
        super(arena);
        this.lastMovement=0;
    }

    @Override
    public void step(Game game, GUI.ACTION action) throws IOException {
        for (Guard guard: getModel().getGuards()){
            guard.setPosition(guard.getPosition());
        }

    }
}

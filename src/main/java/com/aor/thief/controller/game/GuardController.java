package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
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
    public void step(Game game, GUI.ACTION action,long time) throws IOException {
        int index =0;
        int direction=0;
        if (time - lastMovement > 500) {
            for (Guard guard : getModel().getGuards()) {
                while (!getModel().canElementmove(guard.getPosition().movingpattern(direction))) {
                    direction++;
                    if (direction>3) direction=0;
                }
                moveGuard(index, guard.getPosition().movingpattern(direction));
                index++;
            }
            this.lastMovement = time;
            index=0;
        }
    }
    private void moveGuard(int index, Position position) {
        if (getModel().canElementmove(position)){
            getModel().getGuards().get(index).setPosition(position);
        }
    }
}

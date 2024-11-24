package com.aor.thief.controller.game;
import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;

import java.io.IOException;

public class ThiefController extends GameController{
    public ThiefController(Arena arena){
        super(arena);
    }

    public void moveThiefLeft(){
        moveThief(getModel().getThief().getPosition().getLeft());
    }

    public void moveThiefUp(){
        moveThief(getModel().getThief().getPosition().getUp());
    }

    public void moveThiefRight(){
        moveThief(getModel().getThief().getPosition().getRight());
    }

    public void moveThiefDown(){
        moveThief(getModel().getThief().getPosition().getDown());
    }

    private void moveThief(Position position){
        if (getModel().canElementmove(position)) {
            getModel().getThief().setPosition(position);

        }
    }

    @Override
    public void step(Game game, GUI.ACTION action,long time) throws IOException {
        if(action == GUI.ACTION.UP) moveThiefUp();
        if(action == GUI.ACTION.DOWN) moveThiefDown();
        if(action == GUI.ACTION.LEFT) moveThiefLeft();
        if(action == GUI.ACTION.RIGHT) moveThiefRight();
    }
}

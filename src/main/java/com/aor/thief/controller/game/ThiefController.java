package com.aor.thief.controller.game;
import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.MenuState;

import java.io.IOException;

public class ThiefController extends GameController {
    public ThiefController(Arena arena) {
        super(arena);
    }

    public void moveThiefLeft() {
        getModel().getThief().setDirection(1);
        moveThief(getModel().getThief().getPosition().getLeft());
    }

    public void moveThiefUp() {
        getModel().getThief().setDirection(2);
        moveThief(getModel().getThief().getPosition().getUp());
    }

    public void moveThiefRight() {
        getModel().getThief().setDirection(3);
        moveThief(getModel().getThief().getPosition().getRight());
    }

    public void moveThiefDown() {
        getModel().getThief().setDirection(0);
        moveThief(getModel().getThief().getPosition().getDown());
    }

    public boolean canThiefEscape() {
        return getModel().getThief().getPosition().equals(getModel().getDoor().getPosition()) && getModel().getPrize().isEmpty();
    }

    private void moveThief(Position position) {
        if (getModel().isntWall(position)) {
            getModel().getThief().setPosition(position);
        }

    }

    public boolean canKill() {
        int index = 0;
        for (Guard guard : getModel().getGuards()) {
            for (Position kill : getModel().kill_range(getModel().getThief())) {
                if (kill.equals(guard.getPosition())) {
                    getModel().getGuards().remove(index);
                    return true;
                }
            }
            index++;
        }
        return false;
    }

    public void PickedUpPrize(Game game){
        if (!getModel().getPrize().isEmpty()) {
            if (getModel().pickedupPrize(getModel().getThief().getPosition())) {
                getModel().getThief().prize_collected();
                game.playItemPickupSound();
                getModel().getPrize().removeFirst();
            }
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

        if (action == GUI.ACTION.UP) {
            game.playFootStepSound();
            moveThiefUp();
            PickedUpPrize(game);
        }

        if (action == GUI.ACTION.DOWN) {
            game.playFootStepSound();
            moveThiefDown();
            PickedUpPrize(game);
        }

        if (action == GUI.ACTION.LEFT) {
            game.playFootStepSound();
            moveThiefLeft();
            PickedUpPrize(game);
        }
        if (action == GUI.ACTION.RIGHT) {
            game.playFootStepSound();
            moveThiefRight();
            PickedUpPrize(game);
        }
        if (action == GUI.ACTION.ESCAPE) {
            if (canThiefEscape()) {
                game.setState(new MenuState(new Menu()));
            }
        }
        if (action == GUI.ACTION.KILL) {
            game.playGunShot();
             if(canKill()) game.playGuardDeathSound();

        }
    }
}
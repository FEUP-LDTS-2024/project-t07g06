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
        moveThief(getModel().getThief().getPosition().getLeft());
    }

    public void moveThiefUp() {
        moveThief(getModel().getThief().getPosition().getUp());
    }

    public void moveThiefRight() {
        moveThief(getModel().getThief().getPosition().getRight());
    }

    public void moveThiefDown() {
        moveThief(getModel().getThief().getPosition().getDown());
    }

    public boolean canThiefEscape() {
        return getModel().getThief().getPosition().equals(getModel().getDoor().getPosition()) && getModel().getPrize().isEmpty();
    }

    private void moveThief(Position position) {
        if (getModel().isntWall(position)) {
            getModel().getThief().setPosition(position);
        }
        if (!getModel().getPrize().isEmpty()) {
            if (getModel().pickedupPrize(position)) {
                getModel().getThief().prize_collected();
                getModel().getPrize().removeFirst();
            }
        }
        getModel().getThief().toggleFeet();
        //System.out.println(getModel().getThief().getDirection());
        //System.out.println("Thief: " + getModel().getThief().getPosition().getX() + " " + getModel().getThief().getPosition().getY());

    }

    public boolean canKill() throws InterruptedException {
        int index = 0;
        for (Guard guard : getModel().getGuards()) {
            for (Position kill : getModel().kill_range(getModel().getThief())) {
                if (kill.equals(guard.getPosition())) {
                    Thread.sleep(1000);
                    getModel().getGuards().remove(index);
                    return true;
                }
            }
            index++;
        }
        return false;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, InterruptedException {
        if (action == GUI.ACTION.UP) {
            if(getModel().getThief().getDirection()!=2) getModel().getThief().move("up");

            moveThiefUp();
        }
        if (action == GUI.ACTION.DOWN) {
            if(getModel().getThief().getDirection()!=0) getModel().getThief().move("down");
            moveThiefDown();
        }
        if (action == GUI.ACTION.LEFT) {
            if(getModel().getThief().getDirection()!=1) getModel().getThief().move("left");
            moveThiefLeft();
        }
        if (action == GUI.ACTION.RIGHT) {
            if(getModel().getThief().getDirection()!=3) getModel().getThief().move("right");
            moveThiefRight();
        }
        if (action == GUI.ACTION.ESCAPE) {
            if (canThiefEscape()) {
                game.setState(new MenuState(new Menu()));
            }
        }
        if (action == GUI.ACTION.KILL) {
             canKill();

        }
    }
}
package com.aor.thief.controller.game;
import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.DeadBody;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.MenuState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        for(Position p: getModel().getThief().getHitbox()) {
            if(!getModel().getDoor().getHitbox().isEmpty()&&getModel().getDoor().getHitbox().contains(p)&&getModel().getPrize().isEmpty()){
               return true;
            }
        }
        return false;
    }

    private void moveThief(Position position) {
        boolean flag = true;
        for(Position p: position.updatehitbox_()) {
            if (!getModel().isntWall(p)) {
                flag = false;
            }
        }
        if (flag) {
            getModel().getThief().setPosition(position);
            getModel().getThief().setHitbox(position.updatehitbox_());
        }
    }

    public boolean canKill() {
        int index = 0;
        for (Guard guard : getModel().getGuards()) {
            for (Position kill : getModel().fov(getModel().getThief())) {
                if (guard.getHitbox().contains(kill)) {
                    killGuard(guard);
                    return true;
                }
            }
            for (Cop cop : getModel().getCops()) {
                for (Position kill : getModel().fov(getModel().getThief())) {
                    if (cop.getHitbox().contains(kill)) {
                        killCop(cop);
                        return true;
                    }
                }
                index++;
            }
        }
        return false;
    }

    public void PickedUpPrize(Game game){
        if (!getModel().getPrize().isEmpty()) {
            if(getModel().getThief().getHitbox().contains(getModel().getPrize().getFirst().getPosition())) {
                getModel().getThief().prize_collected();
                game.playItemPickupSound();
                getModel().getPrize().removeFirst();
            }
        }
    }

    public void killGuard(Guard guard){
        getModel().addDead(new DeadBody(guard.getPosition().getX(), guard.getPosition().getY(),guard.getHitbox()));
        getModel().getGuards().remove(guard);
    }

    public void killCop(Cop cop){
        getModel().addDead(new DeadBody(cop.getPosition().getX(), cop.getPosition().getY(),cop.getHitbox()));
        getModel().getCops().remove(cop);
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
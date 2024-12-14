package com.aor.thief.controller.game;
import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.MenuState;

import java.io.IOException;

public class ThiefController extends GameController {
    Thread moveAnimation;
    Thread attackAnimation;
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
        getModel().getThief().toggleFeet();
        if (getModel().isntWall(position)) {
            getModel().getThief().setPosition(position);
        }
    }

    public boolean canKill() throws InterruptedException {
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
    private synchronized void startMoveAnimation(Thief thief)  {
        String direction = "";
        switch (thief.getDirection()){
            case 0: direction = "down"; break;
            case 1: direction = "left"; break;
            case 2: direction = "up"; break;
            case 3: direction = "right"; break;
        }
        System.out.println( direction);
        if (moveAnimation == null || !moveAnimation.isAlive()) {
            String finalDirection = direction;
            moveAnimation = new Thread(() -> {
                try {
                    if(getModel().getThief().getFeet()==0) {
                        thief.setDraw("rightleg_" + finalDirection + ".png");
                        Thread.sleep(250);
                    }else {
                        thief.setDraw("leftleg_" + finalDirection + ".png");
                        Thread.sleep(250);
                    }
                    thief.toggleFeet();
                    thief.setDraw("still_" + finalDirection + ".png");
                    Thread.sleep(75);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            moveAnimation.start();
        }
    }

    private synchronized void startAttackAnimation(Thief thief)  {
        String direction = "";
        switch (thief.getDirection()) {
            case 0:
                direction = "down";
                break;
            case 1:
                direction = "left";
                break;
            case 2:
                direction = "up";
                break;
            case 3:
                direction = "right";
                break;
        }
        if (attackAnimation == null || !attackAnimation.isAlive()) {
            String finalDirection = direction;
            attackAnimation = new Thread(() -> {
                try {
                    String shoot = "shoot_" + finalDirection + ".png";

                    thief.setDraw(shoot);
                    Thread.sleep(75);

                    String still = "still_" + finalDirection + ".png";
                    thief.setDraw(still);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    attackAnimation = null;
                }
            });
            attackAnimation.start();
        }
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

    public void step(Game game, GUI.ACTION action, long time) throws IOException, InterruptedException {
        if (action == GUI.ACTION.UP) {
            game.playFootStepSound();
            startMoveAnimation(getModel().getThief());
            if(getModel().getThief().getDirection()!=2) getModel().getThief().move("up");
            moveThiefUp();
            PickedUpPrize(game);
        }

        if (action == GUI.ACTION.DOWN) {
            game.playFootStepSound();
            startMoveAnimation(getModel().getThief());
            if(getModel().getThief().getDirection()!=0) getModel().getThief().move("down");



            moveThiefDown();
            PickedUpPrize(game);
        }

        if (action == GUI.ACTION.LEFT) {
            game.playFootStepSound();
            startMoveAnimation(getModel().getThief());
            if(getModel().getThief().getDirection()!=1) getModel().getThief().move("left");
            moveThiefLeft();
            PickedUpPrize(game);
        }
        if (action == GUI.ACTION.RIGHT) {

            game.playFootStepSound();
            startMoveAnimation(getModel().getThief());
            if(getModel().getThief().getDirection()!=3) getModel().getThief().move("right");
            moveThiefRight();
            PickedUpPrize(game);
        }
        if (action == GUI.ACTION.ESCAPE) {
            if (canThiefEscape()) {
                game.setState(new MenuState(new Menu()));
            }
        }
        if (action == GUI.ACTION.KILL) {

            startAttackAnimation(getModel().getThief());
            game.playGunShot();
            if(canKill()) game.playGuardDeathSound();

            game.playGunShot();



        }
    }
}
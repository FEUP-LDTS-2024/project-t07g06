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
import java.util.ArrayList;
import java.util.List;

public class GuardController extends GameController {
    private long lastMovement;
    private Thread moveAnimation;

    public GuardController(Arena arena) {
        super(arena);
        this.lastMovement = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        int index = 0;
        if(isCaught()){
            game.setState(new MenuState(new Menu()));}
        if (time - lastMovement > 500) {
            for (Guard guard : getModel().getGuards()) {
                startMoveAnimation(guard);
                int cur_dir = guard.getDirection();
                int counting_dir=cur_dir;
                while (!getModel().isntWall(guard.getPosition().movingpattern(cur_dir))) {
                    cur_dir++;
                    counting_dir++;
                    if (cur_dir > 3) cur_dir = 0;
                    if(counting_dir>cur_dir+9) break;
                }
                guard.setDirection(cur_dir);
                moveGuard(index, guard.getPosition().movingpattern(cur_dir));
                System.out.println(cur_dir);
                index++;
            }
            this.lastMovement = time;
            if(isCaught()){
                game.setState(new MenuState(new Menu()));}
        }
    }
    public boolean isCaught() {
        for (Guard guard : getModel().getGuards()) {
            for (Position caught : getModel().fov(guard)){
                if (caught.equals(getModel().getThief().getPosition())){return true;}
            }
        }
        return false;
    }
    private void moveGuard(int index, Position position) {
        if (getModel().isntWall(position)) {
            getModel().getGuards().get(index).setPosition(position);
        }
        getModel().getGuards().get(index).toggleFeet();
    }
    private synchronized void startMoveAnimation(Guard guard) {
        String direction = "";
        switch (guard.getDirection()) {
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
        System.out.println(direction);
        if (moveAnimation == null || !moveAnimation.isAlive()) {
            String finalDirection = direction;
            moveAnimation = new Thread(() -> {
                try {
                    guard.setDraw("rightleg_" + finalDirection + ".png");
                    Thread.sleep(250);
                    guard.setDraw("leftleg_" + finalDirection + ".png");
                    Thread.sleep(250);
                    guard.setDraw("still_" + finalDirection + ".png");
                    Thread.sleep(75);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
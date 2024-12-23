package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.*;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.gameover.GameOver;
import com.aor.thief.states.GameOverState;
import java.io.IOException;

public class GuardController extends GameController {
    private long lastMovement;
    private Thread moveAnimation;

    public GuardController(Arena arena) {
        super(arena);
        this.lastMovement = 0;
    }

    private synchronized void startMoveAnimation(Guard guard) {
        String direction = switch (guard.getDirection()) {
            case 0 -> "down1";
            case 1 -> "left1";
            case 2 -> "up1";
            case 3 -> "right1";
            default -> "";
        };
        if (moveAnimation == null || !moveAnimation.isAlive()) {
            moveAnimation = new Thread(() -> {
                try {
                    if (guard.getFeet() == 0) {
                        guard.setDraw("rightleg_" + direction + ".png");
                    } else {
                        guard.setDraw("leftleg_" + direction + ".png");
                    }
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            moveAnimation.start();
        }
    }

    public boolean isCaught() {
        for (Guard guard : getModel().getGuards()) {
            for (Position caught : getModel().fov(guard)) {
                if (getModel().getThief().getHitbox().contains(caught)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void moveGuard(int index, Position position) {
        boolean flag = true;
        for (Position p : position.updatehitbox_()) {
            if (!getModel().isntWall(p)) {
                flag = false;
            }
        }
        if (flag) {
            getModel().getGuards().get(index).setPosition(position);
            getModel().getGuards().get(index).setHitbox(position.updatehitbox_());
        }
        getModel().getGuards().get(index).toggleFeet();
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        int index = 0;
        if (!getModel().getGuards().isEmpty()) {
            if (time - lastMovement > 500) {
                if (!getModel().getGuards().getFirst().isFound()) {
                    for (Guard guard : getModel().getGuards()) {
                        int cur_dir = guard.getDirection();
                        int counting_dir = cur_dir;
                        for (Position position : guard.getHitbox()) {
                            startMoveAnimation(guard);
                            while (!getModel().isntWall(position.movingpattern(cur_dir))) {
                                cur_dir++;
                                counting_dir++;
                                if (cur_dir > 3) cur_dir = 0;
                                if (counting_dir > cur_dir + 7) break;
                            }
                        }
                        guard.setDirection(cur_dir);
                        moveGuard(index, guard.getPosition().movingpattern(cur_dir));
                        index++;
                    }
                    this.lastMovement = time;
                    if (isCaught()) {
                        game.stopGameSound();game.playWantedSound();
                        for (Guard guard : getModel().getGuards()) {
                            guard.setFound(true);
                        }
                        for (Cop cop : getModel().getCops()) {
                            cop.setFound(true);
                        }
                        for (Camera camera : getModel().getCamera()){
                            camera.setFound(true);
                        }
                        for (CameraStill still : getModel().getStillCameras()){
                            still.setFound(true);
                        }
                    }
                } else {
                    for (Guard guard : getModel().getGuards()) {
                        pursuit(guard, getModel().getThief(), index);
                        startMoveAnimation(guard);
                        if (guard.getPosition().equals(getModel().getThief().getPosition())) {
                            game.playLossSound();
                            game.setState(new GameOverState(new GameOver()));
                        }
                        index++;
                        this.lastMovement = time;
                    }
                }
            }
        }
    }
    public void pursuit(Guard guard, Thief thief, int guard_index) {
        int x = Integer.compare(thief.getPosition().getX(), guard.getPosition().getX());
        int y = Integer.compare(thief.getPosition().getY(), guard.getPosition().getY());
        if(x==1||x==-1) y=0;
        if(x==1){
            guard.setDirection(3);
        }else if(x==-1){
            guard.setDirection(1);
        }else if(y==-1){
            guard.setDirection(2);
        }else if(y==1){
            guard.setDirection(0);
        }
        moveGuard(guard_index, new Position(guard.getPosition().getX()+x, guard.getPosition().getY() + y));
    }
}
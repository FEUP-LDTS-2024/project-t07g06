package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.*;
import com.aor.thief.model.gameover.GameOver;
import com.aor.thief.states.GameOverState;

import java.io.IOException;

public class CopController extends GameController {
    private long lastMovement;
    private Thread moveAnimation;

    public CopController(Arena arena) {
        super(arena);
        this.lastMovement = 0;
    }

    public boolean isCaught() {
        for (Cop cop : getModel().getCops()) {
            for (Position caught : getModel().fov(cop)) {
                if (getModel().getThief().getHitbox().contains(caught)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void moveCop(int index, Position position) {
        boolean flag = true;
        for(Position p: position.updatehitbox_()) {
            if (!getModel().isntWall(p)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            getModel().getCops().get(index).setPosition(position);
            getModel().getCops().get(index).setHitbox(position.updatehitbox_());
        }
    }
    public void pursuit(Cop cop, Thief thief, int cop_index) {
        int x = Integer.compare(thief.getPosition().getX(), cop.getPosition().getX());
        int y = Integer.compare(thief.getPosition().getY(), cop.getPosition().getY());
        moveCop(cop_index, new Position(cop.getPosition().getX()+x, cop.getPosition().getY() + y));
    }
    public Position movement(Position position, int direction, int index){
        switch (direction){
            case 1:
                return position.getLeft();
            case 3:
                return position.getRight();
            default:
                if(getModel().getCops().get(index).isDown()) return position.getDown();
                else return position.getUp();
        }
    }
    private synchronized void startMoveAnimation(Cop cop) {
        String direction = "";
        switch (cop.getDirection()) {
            case 0:
                direction = "down1";
                break;
            case 1:
                direction = "left1";
                break;
            case 2:
                direction = "up1";
                break;
            case 3:
                direction = "right1";
                break;
        }
        if (moveAnimation == null || !moveAnimation.isAlive()) {
            String finalDirection = direction;
            moveAnimation = new Thread(() -> {
                try {
                    if (cop.getFeet() == 0) {
                        cop.setDraw("rightleg_" + finalDirection + ".png");
                        cop.setDraw("leftleg_" + finalDirection + ".png");
                    }
                    cop.toggleFeet();
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            moveAnimation.start();
        }
    }
    public void caught(){
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

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        int index = 0;
        if (!getModel().getCops().isEmpty()) {
            if (time - lastMovement > 500) {
                if (!getModel().getCops().getFirst().isFound()) {
                    for (Cop cop : getModel().getCops()) {
                        int cur_dir = cop.getDirection();
                        int counting_dir = cur_dir;
                        startMoveAnimation(cop);
                        for (Position position : cop.getHitbox()) {
                            if (!getModel().isntWall(position.getDown())) {
                                cop.setDown(false);
                            }
                            if (!getModel().isntWall(position.getUp())) {
                                cop.setDown(true);
                            }
                            if (!getModel().isntWall(movement(position, cur_dir, index))) {
                                cur_dir++;
                                counting_dir++;
                                if (cur_dir > 3) cur_dir = 0;
                                if (counting_dir > cur_dir + 7) break;
                            }
                            if (cur_dir % 2 == 0) {
                                cop.setDirection(cur_dir + 1);
                            }else cop.setDirection(cur_dir);
                        }
                        moveCop(index, movement(cop.getPosition(), cur_dir, index));
                        index++;
                    }
                    this.lastMovement = time;
                    if (isCaught()) {
                        game.stopGameSound();game.playWantedSound();
                        caught();
                    }
                } else {
                    for (Cop cop : getModel().getCops()) {
                        pursuit(cop, getModel().getThief(), index);
                        startMoveAnimation(cop);
                        if (cop.getPosition().equals(getModel().getThief().getPosition())) {
                            game.stopGameSound();
                            game.stopWantedSound();
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
}
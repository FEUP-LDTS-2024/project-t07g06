package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.Guard;


import java.io.IOException;
import java.util.*;

public class CameraController extends GameController {
    private long lastMovement;
    private Thread moveAnimation;

    public CameraController(Arena arena) {
        super(arena);
        this.lastMovement = 0;
    }


    public boolean isCaught() {
        for (Camera camera : getModel().getCamera()) {
            for (Position caught : getModel().fov(camera)) {
                if (getModel().getThief().getHitbox().contains(caught)) {
                    return true;
                }
            }

        }
        return false;
    }

    private void rotateCamera(int index,int current_pos) {
        getModel().getCamera().get(index).setDirection(getModel().getCamera().get(index).getCurrent_pos());
        int movement=0;
        switch (current_pos) {
            case 0:
                getModel().getCamera().get(index).setDown(true);
                movement = 1;
                break;
            case 1:
                if(getModel().getCamera().get(index).isDown()) movement=1;
                else movement=-1;
                break;
            case 2:
                getModel().getCamera().get(index).setDown(false);
                movement=-1;
                break;
        }
        getModel().getCamera().get(index).setCurrent_pos(getModel().getCamera().get(index).getCurrent_pos()+movement);
    }
    private synchronized void startRotationAnimation(Camera camera) {
        String pos = "";
        List<Integer> corner = camera.getCorner();
        if(!corner.isEmpty()) {
            if (corner.getFirst() == 0) {
                pos += "up_";
            } else {
                pos += "down_";
            }
            if (corner.getLast() == 3) {
                pos += "left_";
            } else {
                pos += "right_";
            }
        }
        if (moveAnimation == null || !moveAnimation.isAlive()) {
            if (!camera.getCorner().isEmpty()) {
                int i = camera.getCorner().get(camera.getCurrent_pos());
                String finalPos = pos;
                moveAnimation = new Thread(() -> {
                    try {
                        switch (camera.getCorner().indexOf(i)) {
                            case 0:
                                camera.setDraw(finalPos + "up.png");
                                break;
                            case 1:
                                camera.setDraw(finalPos + "mid.png");
                                break;
                            case 2:
                                camera.setDraw(finalPos + "down.png");
                                break;
                        }
                        Thread.sleep(750);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                moveAnimation.start();
            }
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - lastMovement > 750) {
            if (!getModel().getCamera().isEmpty()) {
                if (isCaught()) {
                    for (Guard guard : getModel().getGuards()) {
                        guard.setFound(true);
                    }
                    for (Cop cop : getModel().getCops()) {
                        cop.setFound(true);
                    }
                }
                int index=0;
                for (Camera camera : getModel().getCamera()) {
                    startRotationAnimation(camera);
                    if(camera.getCorner().isEmpty()){
                        getModel().defCorner(camera);
                    }
                    rotateCamera(index,camera.getCurrent_pos());
                    getModel().fov(camera);
                    index++;
                }
                this.lastMovement = time;
                if (isCaught()) {
                    game.stopGameSound();
                    game.playWantedSound();
                    for (Guard guard : getModel().getGuards()) {
                        guard.setFound(true);
                    }
                    for (Cop cop : getModel().getCops()) {
                        cop.setFound(true);
                    }
                }
            }
        }
    }
}
package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.MenuState;

import java.io.IOException;
import java.util.*;

public class CameraController extends GameController {
    private long lastMovement;
    private int cur_dir;

    public CameraController(Arena arena) {
        super(arena);
        this.lastMovement = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - lastMovement > 1000) {
            if (!getModel().getGuards().isEmpty()) {
                if (isCaught()) {
                    for (Guard guard : getModel().getGuards()) {
                        guard.setFound(true);
                    }
                    for (Cop cop : getModel().getCops()) {
                        cop.setFound(true);
                    }
                }
                int index=0;
                if (!getModel().getCamera().getFirst().isFound()) {
                    for (Camera camera : getModel().getCamera()) {
                        if(camera.getCorner().isEmpty()){
                            getModel().defCorner(camera);
                        }
                        rotateCamera(index,camera.getCurrent_pos());
                        getModel().fov(camera);
                        index++;
                    }
                    this.lastMovement = time;
                    if (isCaught()) {
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

}
package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Camera;
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
        int index = 0;
        if (time - lastMovement > 500) {
            if (!getModel().getGuards().isEmpty()) {
                if (isCaught()) {
                    for (Guard guard : getModel().getGuards()) {
                        guard.setFound(true);
                    }
                }
                if (!getModel().getGuards().getFirst().isFound()) {
                    for (Guard guard : getModel().getGuards()) {
                        int cur_dir = guard.getDirection();
                        int counting_dir = cur_dir;
                        while (!getModel().isntWall(guard.getPosition().movingpattern(cur_dir))) {
                            cur_dir++;
                            counting_dir++;
                            if (cur_dir > 3) cur_dir = 0;
                            if (counting_dir > cur_dir + 9) break;
                        }
                        guard.setDirection(cur_dir);
                        index++;
                    }
                    this.lastMovement = time;
                    if (isCaught()) {
                        for (Guard guard : getModel().getGuards()) {
                            guard.setFound(true);
                        }
                        //game.setState(new MenuState(new Menu()));}
                    }
                } else {
                    for (Guard guard : getModel().getGuards()) {
                        Map<Position, Boolean> path = new HashMap<>();
                        if (guard.getPosition().equals(getModel().getThief().getPosition())) {
                            game.setState(new MenuState(new Menu()));
                        }
                        index++;
                        this.lastMovement = time;
                    }

                }
            }
        }
    }

    public boolean isCaught() {
        for (Camera camera : getModel().getCamera()) {
            for (Position caught : getModel().fov(getModel().getGuards().getFirst())) {
                if (caught.equals(getModel().getThief().getPosition())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void moveGuard(int index, int direction) {
        getModel().getCamera().get(index).setDirection(direction);
    }

}
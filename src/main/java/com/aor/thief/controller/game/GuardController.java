package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.Guard;

import com.aor.thief.model.game.elements.Thief;

import com.aor.thief.model.gameover.GameOver;

import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.GameOverState;
import com.aor.thief.states.MenuState;

import java.io.IOException;
import java.util.*;

public class GuardController extends GameController {
    private long lastMovement;
    private int cur_dir;

    public GuardController(Arena arena) {
        super(arena);
        this.lastMovement = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        int index = 0;

        if(!getModel().getGuards().isEmpty()) {
            if (time - lastMovement > 500) {
                if (!getModel().getGuards().isEmpty()) {
                    if (isCaught()) {
                        for (Guard guard : getModel().getGuards()) {
                            guard.setFound(true);
                        }
                        for (Cop cop : getModel().getCops()) {
                            cop.setFound(true);
                        }
                    }
                    if (!getModel().getGuards().getFirst().isFound()) {
                        for (Guard guard : getModel().getGuards()) {
                            int cur_dir = guard.getDirection();
                            int counting_dir = cur_dir;
                            if (!guard.getHitbox().isEmpty()) {
                                for (Position position : guard.getHitbox()) {
                                    while (!getModel().isntWall(position.movingpattern(cur_dir))) {
                                        cur_dir++;
                                        counting_dir++;
                                        if (cur_dir > 3) cur_dir = 0;
                                        if (counting_dir > cur_dir + 9) break;
                                    }
                                }
                            }
                            guard.setDirection(cur_dir);
                            moveGuard(index, guard.getPosition().movingpattern(cur_dir));
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
                    } else {
                        for (Guard guard : getModel().getGuards()) {
                            Map<Position, Boolean> path = new HashMap<>();
                            pursuit(guard, getModel().getThief(), index);
                            if (guard.getPosition().equals(getModel().getThief().getPosition())) {
                                game.stopGameSound();
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
        for(Position p: position.updatehitbox_()) {
            if (!getModel().isntWall(p)) {
                flag = false;
            }
        }
        if (flag) {
            getModel().getGuards().get(index).setPosition(position);
            getModel().getGuards().get(index).setHitbox(position.updatehitbox_());
        }
    }



    List<Position> getNeighbors(Position p) {
        List<Position> neighbors = new ArrayList<>();
        int x = p.getX();
        int y = p.getY();
        neighbors.add(new Position(x - 1, y));
        neighbors.add(new Position(x + 1, y));
        neighbors.add(new Position(x, y - 1));
        neighbors.add(new Position(x, y + 1));
        return neighbors;
    }




    public void pursuit(Guard guard, Thief thief, int guard_index) {
        int min_dist = Integer.MAX_VALUE;
        Position nierest = null;
        for(Position p: getNeighbors(guard.getPosition())) {
            if(p.distance(thief.getPosition())<min_dist) {
                min_dist = p.distance(thief.getPosition());
                nierest = p;
            }
        }
        moveGuard(guard_index, new Position(nierest.getX(), nierest.getY()));
    }
}
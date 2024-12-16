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
                        moveGuard(index, guard.getPosition().movingpattern(cur_dir));
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
                        pursuit(guard, getModel().getThief(),index);
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
        for (Guard guard : getModel().getGuards()) {
            for (Position caught : getModel().fov(guard)) {
                if (caught.equals(getModel().getThief().getPosition())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void moveGuard(int index, Position position) {
        if (getModel().isntWall(position)) {
            getModel().getGuards().get(index).setPosition(position);
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
        int x = guard.getPosition().getX();
        int y = guard.getPosition().getY();
        x += ((thief.getPosition().getX()<x)? -1 : (thief.getPosition().getX()>x) ? 1 : 0 );
        y += ((thief.getPosition().getY()<y)? -1 : (thief.getPosition().getY()>y) ? 1 : 0 );
        moveGuard(guard_index, new Position(x,y));
    }
}
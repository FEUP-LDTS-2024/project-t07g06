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
    private int cur_dir;

    public GuardController(Arena arena) {
        super(arena);
        this.lastMovement = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        int index = 0;
        if (time - lastMovement > 500) {
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
            }else {
                for (Guard guard : getModel().getGuards()) {
                    pursuit(guard, getModel().getThief(), index);
                    if(guard.getPosition().equals(getModel().getThief().getPosition())) {
                        game.setState(new MenuState(new Menu()));
                    }
                    index++;
                    this.lastMovement = time;
                }

            }
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
    }
    public void pursuit(Guard guard, Thief thief, int guard_index) {
        List<Position> path = new ArrayList<>();
        int min_dist=Integer.MAX_VALUE;
        int index=0;
        int x=guard.getPosition().getX();
        int y=guard.getPosition().getY();
        if(getModel().isntWall(new Position(x,y-1))) path.add(new Position(x,y-1));
        if(getModel().isntWall(new Position(x-1,y))) path.add(new Position(x-1,y));
        if(getModel().isntWall(new Position(x,y+1)))path.add(new Position(x,y+1));
        if(getModel().isntWall(new Position(x+1,y)))path.add(new Position(x+1,y));
        for (int i = 0; i < path.size(); i++) {
            if(path.get(i).distance(thief.getPosition())<min_dist){
                min_dist=path.get(i).distance(thief.getPosition());
                index=i;

            }
        }
        moveGuard(guard_index,path.get(index));
    }
}
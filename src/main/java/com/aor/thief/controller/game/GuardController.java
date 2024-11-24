package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Guard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuardController extends GameController{
    private long lastMovement;
    private int cur_dir;

    public GuardController(Arena arena){
        super(arena);
        this.lastMovement=0;
    }

    @Override
    public void step(Game game, GUI.ACTION action,long time) throws IOException {
        int index =0;
        if (time - lastMovement > 500) {
            for (Guard guard : getModel().getGuards()) {
                 cur_dir= guard.getDirection();
                while (!getModel().isntWall(guard.getPosition().movingpattern(cur_dir))) {
                    cur_dir++;
                    if (cur_dir>3) cur_dir=0;
                }
                guard.setDirection(cur_dir);
                moveGuard(index, guard.getPosition().movingpattern(cur_dir));
                index++;
            }
            this.lastMovement = time;
            index=0;
        }
    }
    private void moveGuard(int index, Position position) {
        if (getModel().isntWall(position)){
            getModel().getGuards().get(index).setPosition(position);
        }
    }

    private List<Position> fov(int index, Position position){
        List<Position> FOV = new ArrayList<>();
        Position guardCurrentPosition = getModel().getGuards().get(index).getPosition();
        int sight = 3;

        int dirx=0, diry=0;

        switch (cur_dir){
            case 0:
                dirx =0;
                diry =1;
                break;
            case 1:
                dirx =-1;
                diry =0;
                break;
            case 2:
                dirx =0;
                diry =-1;
                break;
            case 3:
                dirx =1;
                diry =0;
                break;

        }

        for(int i=0; i <= sight;i++){
            int fovx= guardCurrentPosition.getX() + (dirx * i);
            int fovy= guardCurrentPosition.getY() + (diry * i);

            Position possiblyCanSee= new Position(fovx, fovy);

            if(getModel().isntWall(possiblyCanSee)){
                FOV.add(possiblyCanSee);
            }
        }
    return FOV;
    }
}

package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OnlyFrontFov extends HasFov{
    public OnlyFrontFov(int x, int y, int direction, List<Position> hitbox) {
        super(x, y, direction, hitbox);
    }

    @Override
    public  int getSight(){
        return 50;
    }

    @Override
    public void defineFov(Arena arena, int sig) {
        List<Position> FOV = new ArrayList<>();
        Position CurrentPosition = getPosition();
        int sight = 6;

        int dirx = 0, diry = 0;

        switch (getDirection()) {
            case 0:
                dirx = 0;
                diry = 1;
                break;
            case 1:
                dirx = -1;
                diry = 0;
                break;
            case 2:
                dirx = 0;
                diry = -1;
                break;
            case 3:
                dirx = 1;
                diry = 0;
                break;

        }
        boolean flag=true;
        for (int i = 0; i <= sight; i++) {
            int fovx = CurrentPosition.getX() + dirx * i *2 ;
            int fovy = CurrentPosition.getY() + diry * i ;
            Position possiblyCanSee = new Position(fovx, fovy);

            if (arena.isntWall(possiblyCanSee)) {
                FOV.add(possiblyCanSee);
            } else {
                break;
                }
            }
        this.fov=FOV;
        }

    }


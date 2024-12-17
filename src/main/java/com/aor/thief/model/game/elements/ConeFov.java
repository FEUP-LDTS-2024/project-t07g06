package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class ConeFov extends HasFov{
    public ConeFov(int x, int y, int direction, List<Position> hitbox) {
        super(x, y, direction,hitbox);
    }

    @Override
    public int getSight() {
        return 3;
    }

    @Override
    public void defineFov(Arena arena, int sig){
        List<Position> FOV = new ArrayList<>();
        HashSet<Integer> intSet = new HashSet<>();
        Position guardCurrentPosition = getPosition();


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
        for (int i = 0; i <= sig; i++) {
            for (int j = -i ; j <= i ; j++){
                int fovx = guardCurrentPosition.getX() + dirx * i + diry * j;
                int fovy = guardCurrentPosition.getY() + diry * i + dirx * j;
                Position possiblyCanSee = new Position(fovx, fovy);

                if (arena.isntWall(possiblyCanSee) && !intSet.contains(j)) {
                    FOV.add(possiblyCanSee);
                } else {
                    intSet.add(j);
                    if(j<i+0.5){for(int o=-sig;o<j;o++){intSet.add(o);}}
                    if(j>i+0.5){for(int o=j;o<=sig;o++){intSet.add(o);}}


                }

            }
        }
        this.fov=FOV;
    }
}

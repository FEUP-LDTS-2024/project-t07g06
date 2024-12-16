package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Guard extends HasFov {
    private int direction;
    private boolean found;

    public Guard(int x, int y, int direction, boolean found) {
        super(x, y);
        this.direction = direction;
        this.found = found;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    @Override
    public void defineFov(Arena arena) {
        List<Position> FOV = new ArrayList<>();
        HashSet<Integer> intSet = new HashSet<>();
        Position guardCurrentPosition = getPosition();
        int sight = 2;

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
        for (int i = 0; i <= sight; i++) {
            for (int j = -i ; j <= i ; j++){
                int fovx = guardCurrentPosition.getX() + dirx * i + diry * j;
                int fovy = guardCurrentPosition.getY() + diry * i + dirx * j;
                Position possiblyCanSee = new Position(fovx, fovy);

                if (arena.isntWall(possiblyCanSee) && !intSet.contains(j)) {
                    FOV.add(possiblyCanSee);
                } else {
                    intSet.add(j);
                    if(j<i+0.5){for(int o=-sight;o<j;o++){intSet.add(o);}}
                    if(j>i+0.5){for(int o=j;o<=sight;o++){intSet.add(o);}}


                }

            }
        }
        this.fov=FOV;
    }

}

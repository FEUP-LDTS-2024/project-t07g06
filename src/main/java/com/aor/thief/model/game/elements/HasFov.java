package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class HasFov extends Element{
    protected List<Position> fov;
    int direction;

    public HasFov(int x, int y,int direction) {
        super(x, y);
        this.direction=direction;

    }
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }



    public abstract void defineFov(Arena arena,int sig);

    public List<Position> getFov(){
        return fov;
    }

    public abstract int getSight();
}

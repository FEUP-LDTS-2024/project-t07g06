package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;

import java.util.List;

public abstract class HasFov extends Element{
    protected List<Position> fov;


    public HasFov(int x, int y) {
        super(x, y);

    }

    public abstract void defineFov(Arena arena);

    public List<Position> getFov(){
        return fov;
    }
}

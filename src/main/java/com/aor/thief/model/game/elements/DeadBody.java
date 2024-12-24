package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class DeadBody extends Element{
    private String type;

    public DeadBody(int x, int y, List<Position> hitbox,String type) {
        super(x, y,hitbox);
        this.type=type;
    }

    public String getType() {
        return type;
    }


}

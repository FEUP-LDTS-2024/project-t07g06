package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class DeadBody extends Element{

    public DeadBody(int x, int y, List<Position> hitbox) {
        super(x, y,hitbox);
    }
}

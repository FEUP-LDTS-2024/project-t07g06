package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class Cop extends OFEnemy{

    public Cop(int x, int y, int direction, boolean found, List<Position> hitbox) {
        super(x, y, direction, found,hitbox);
    }

}

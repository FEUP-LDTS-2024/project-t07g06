package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class Wall extends Element {
    public Wall(int x, int y, List<Position> hitbox) {
        super(x, y, hitbox);
    }
}

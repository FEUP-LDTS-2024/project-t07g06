package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class Cop extends OFEnemy{
    private List<Position> hitbox;

    public Cop(int x, int y, int direction, boolean found, List<Position> hitbox) {
        super(x, y, direction, found);
        this.hitbox = hitbox;
    }

    public List<Position> getHitbox() {
        return hitbox;
    }

    public void setHitbox(List<Position> hitbox) {
        this.hitbox = hitbox;
    }
}

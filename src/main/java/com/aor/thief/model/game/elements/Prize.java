package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class Prize extends Element{
    List<Position> hitbox;
    public Prize(int x, int y, List<Position> hitbox) {
        super(x, y);
        this.hitbox = hitbox;
    }

    public List<Position> getHitbox() {
        return hitbox;
    }

    public void setHitbox(List<Position> hitbox) {
        this.hitbox = hitbox;
    }
}

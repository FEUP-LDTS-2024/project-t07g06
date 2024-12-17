package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public abstract class Hitbox {
    List<Position> hitbox;

    public List<Position> getHitbox() {
        return hitbox;
    }

    public void setHitbox(List<Position> hitbox) {
        this.hitbox = hitbox;
    }
}

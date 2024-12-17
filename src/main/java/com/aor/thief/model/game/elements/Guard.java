package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Guard extends ConeEnemy {
    private List<Position> hitbox;

    public Guard(int x, int y, int direction, boolean found, List<Position> hitbox) {
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






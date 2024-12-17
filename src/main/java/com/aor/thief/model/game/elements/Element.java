package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class Element {
    private Position position;
    private List<Position> hitbox;

    public Element(int x, int y, List<Position> hitbox) {
        this.position = new Position(x, y);
        this.hitbox = hitbox;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Position> getHitbox() {
        return hitbox;
    }

    public void setHitbox(List<Position> hitbox) {
        this.hitbox = hitbox;
    }
}

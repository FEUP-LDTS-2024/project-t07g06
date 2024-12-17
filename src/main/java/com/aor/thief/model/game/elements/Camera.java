package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class Camera extends Element {
    private int direction;

    public Camera(int x, int y, int direction, List<Position> hitbox) {
        super(x, y,hitbox);
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
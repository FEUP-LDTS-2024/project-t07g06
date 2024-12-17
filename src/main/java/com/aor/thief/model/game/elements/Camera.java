package com.aor.thief.model.game.elements;

public class Camera extends Element {
    private int direction;

    public Camera(int x, int y, int direction) {
        super(x, y);
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
package com.aor.thief.model.game.elements;

public class Guard extends Element {
    private int direction;
    private boolean found;

    public Guard(int x, int y, int direction, boolean found) {
        super(x, y);
        this.direction = direction;
        this.found = found;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}

package com.aor.thief.model.game.elements;

public class OFEnemy extends OnlyFrontFov{
    private boolean found;

    public OFEnemy(int x, int y, int direction, boolean found) {
        super(x, y,direction);
        this.found = found;
    }



    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}

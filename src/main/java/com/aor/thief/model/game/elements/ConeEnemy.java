package com.aor.thief.model.game.elements;

public class ConeEnemy extends ConeFov{
    private boolean found;

    public ConeEnemy(int x, int y, int direction, boolean found) {
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

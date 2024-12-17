package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class OFEnemy extends OnlyFrontFov{
    private boolean found;

    public OFEnemy(int x, int y, int direction, boolean found, List<Position> hitbox) {
        super(x, y,direction,hitbox);
        this.found = found;
    }



    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}

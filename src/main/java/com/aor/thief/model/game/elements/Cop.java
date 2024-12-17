package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class Cop extends OFEnemy{
    boolean isDown=false;
    public Cop(int x, int y, int direction, boolean found, List<Position> hitbox) {
        super(x, y, direction, found,hitbox);
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }
}

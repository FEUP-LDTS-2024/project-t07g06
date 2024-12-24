package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.ArrayList;
import java.util.List;

public class CameraStill extends ConeEnemy {
    List<Integer> corner;

    public CameraStill(int x, int y, int direction, boolean found, List<Position> hitbox,List<Integer> corner) {
        super(x, y, direction, found, hitbox);
        this.corner=corner;
    }
    public List<Integer> getCorner() {
        return corner;
    }

    public void setCorner(List<Integer> corner) {
        this.corner = corner;
    }
}

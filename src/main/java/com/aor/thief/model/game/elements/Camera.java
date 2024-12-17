package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Camera extends ConeEnemy {
    boolean isDown=false;
    List<Integer> corner= new ArrayList<>();
    int current_pos;
    public Camera(int x, int y, int direction, boolean found, List<Position> hitbox) {
        super(x, y, direction, found, hitbox);
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public List<Integer> getCorner() {
        return corner;
    }

    public void setCorner(List<Integer> corner) {
        this.corner = corner;
    }

    public int getCurrent_pos() {
        return current_pos;
    }

    public void setCurrent_pos(int current_pos) {
        this.current_pos = current_pos;
    }
}
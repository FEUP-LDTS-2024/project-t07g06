package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Guard extends ConeEnemy {
    private int feet;
    private String draw;
    public Guard(int x, int y, int direction, boolean found, List<Position> hitbox,int feet, String draw) {
        super(x, y, direction, found,hitbox);
        this.feet = feet;
        this.draw = draw;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public int getFeet() {
        return feet;
    }

    public void setFeet(int feet) {
        this.feet = feet;
    }
    public void toggleFeet() {
        feet = (feet + 1) % 2;
    }
}







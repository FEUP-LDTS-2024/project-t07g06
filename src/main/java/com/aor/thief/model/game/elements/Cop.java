package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.List;

public class Cop extends OFEnemy{
    boolean isDown=false;
    int feet=0;
    String draw;
    public Cop(int x, int y, int direction, boolean found, List<Position> hitbox, int feet,String draw) {
        super(x, y, direction, found,hitbox);
        this.feet = feet;
        this.draw = draw;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }
    public void toggleFeet() {
        feet = (feet + 1) % 2;
    }
    public int getFeet() {
        return feet;
    }

    public void setFeet(int feet) {
        this.feet = feet;
    }
    public String getDraw() {
        return this.draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }
}

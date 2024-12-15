package com.aor.thief.model.game.elements;

public class Guard extends Element {
    private int direction;
    private int feet;
    private String draw;
    public Guard(int x, int y, int direction, int feet, String draw) {
        super(x, y);
        this.direction = direction;
        this.feet = feet;
        this.draw = draw;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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

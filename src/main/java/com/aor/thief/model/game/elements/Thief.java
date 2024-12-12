package com.aor.thief.model.game.elements;

public class Thief extends Element{
    int prize;
    int direction;
    int feet;

    public Thief(int x, int y, int prize,int direction,int feet) {
        super(x, y);
        this.prize = prize;
        this.direction=direction;
        this.feet=feet;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
    public void prize_collected(){
        prize=1;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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

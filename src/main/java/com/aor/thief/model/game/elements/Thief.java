package com.aor.thief.model.game.elements;

public class Thief extends Element{
    int prize;
    int direction;

    public Thief(int x, int y, int prize,int direction) {
        super(x, y);
        this.prize = prize;
        this.direction=direction;
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
}

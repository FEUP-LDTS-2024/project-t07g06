package com.aor.thief.model.game.elements;

public class Thief extends Element{
    int prize;

    public Thief(int x, int y, int prize) {
        super(x, y);
        this.prize = prize;
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
}

package com.aor.thief.model.game.elements;

public class Thief extends OnlyFrontFov{
    int prize;


    public Thief(int x, int y, int prize,int direction) {
        super(x, y,direction);
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

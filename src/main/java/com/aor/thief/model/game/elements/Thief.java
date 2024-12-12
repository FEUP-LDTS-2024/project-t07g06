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
    public void move(String direction){
        switch (direction){
            case("down"):
                setDirection(0);
                break;
            case("left"):
                setDirection(1);
                break;
            case("up"):
                setDirection(2);
                break;
            case("right"):
                setDirection(3);
                break;
        }
    }
}

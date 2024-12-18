package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Thief extends OnlyFrontFov{
    int prize;
    int direction;
    int feet;
    String draw;


    public Thief(int x, int y, int prize,int direction, List<Position> hitbox,int feet,String draw) {
        super(x,y,direction,hitbox);
        this.prize = prize;
        this.feet=feet;
        this.draw = draw;
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

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

}

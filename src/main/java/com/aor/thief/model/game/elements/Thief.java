package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Thief extends OnlyFrontFov{
    int prize;
    List<Position> hitbox;


    public Thief(int x, int y, int prize,int direction, List<Position> hitbox) {
        super(x,y,direction);
        this.prize = prize;
        this.hitbox = hitbox;


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


    public List<Position> getHitbox() {
        return hitbox;
    }

    public void setHitbox(List<Position> hitbox) {
        this.hitbox = hitbox;
    }
}

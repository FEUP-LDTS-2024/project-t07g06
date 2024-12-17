package com.aor.thief.model.game.arena;

import com.aor.thief.model.game.elements.*;
import com.aor.thief.model.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Arena {
    private final int width;
    private final int height;
    private Thief thief;

    private Door door;
    private List<Prize> prize;

    private List<Guard> guards;
    private List<Camera> cameras;
    private List<Wall> walls;
    private List<DeadBody> deads;
    private List<Cop> cops;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Thief getThief() {
        return thief;
    }

    public void setThief(Thief thief) {
        this.thief = thief;
    }

    public List<Guard> getGuards() {
        return guards;
    }

    public void setGuards(List<Guard> guards) {
        this.guards = guards;
    }

    public List<Camera> getCamera() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public boolean isntWall(Position position) {
        for (Wall wall : walls)
            if(wall.getPosition().equals(position)) {
                return false;
            }
//            if (wall.getHitbox().contains(position))
//                return false;
        return true;
    }

    public List<Position> fov(HasFov hasfov) {
        hasfov.defineFov(this,hasfov.getSight());
        return hasfov.getFov();
    }


    public List<Prize> getPrize() {
        return prize;
    }

    public void setPrize(List<Prize> prize) {
        this.prize = prize;
    }

    public boolean pickedupPrize(Position position) {
        if (getPrize().getFirst().getHitbox().contains(position)) {
            return true;
        }
        return false;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public List<DeadBody> getDeads() {
        return deads;
    }

    public void setDeads(List<DeadBody> deads) {
        this.deads = deads;
    }
    public void addDead(DeadBody dead){
        deads.add(dead);
    }

    public List<Cop> getCops() {
        return cops;
    }

    public void setCops(List<Cop> cops) {
        this.cops = cops;
    }
}



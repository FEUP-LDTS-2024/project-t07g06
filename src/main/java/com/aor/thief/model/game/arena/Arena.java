package com.aor.thief.model.game.arena;

import com.aor.thief.model.game.elements.*;
import com.aor.thief.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final int width;
    private final int height;
    private Thief thief;

    private Door door;
    private List<Prize> prize;

    private List<Guard> guards;
    private List<Camera> cameras;
    private List<Wall> walls;


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

    public List<Camera> getCamera() {return cameras;}

    public void setCameras(List<Camera> cameras){
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
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }
    public List<Position> fov(Guard guard) {
        List<Position> FOV = new ArrayList<>();
        Position guardCurrentPosition = guard.getPosition();
        int sight = 3;

        int dirx = 0, diry = 0;

        switch (guard.getDirection()) {
            case 0:
                dirx = 0;
                diry = 1;
                break;
            case 1:
                dirx = -1;
                diry = 0;
                break;
            case 2:
                dirx = 0;
                diry = -1;
                break;
            case 3:
                dirx = 1;
                diry = 0;
                break;

        }
        boolean flag=Boolean.TRUE;
        for (int i = 0; i <= sight &&flag; i++) {
            //for (int j = -i; j <= i; j++) {
                int fovx = guardCurrentPosition.getX() + dirx * i  ;//*j
                int fovy = guardCurrentPosition.getY() + diry * i ;//* j
                Position possiblyCanSee = new Position(fovx, fovy);

                if (isntWall(possiblyCanSee)) {
                    FOV.add(possiblyCanSee);
                } else {
                    flag = Boolean.FALSE;
                    break;
                //}
            }
        }
        return FOV;
    }

    public List<Position> kill_range(Thief thief) {
        List<Position> kill_range = new ArrayList<>();
        Position thiefCurrentPosition = thief.getPosition();
        int sight = 6;

        int dirx = 0, diry = 0;

        switch (thief.getDirection()) {
            case 0:
                dirx = 0;
                diry = 1;
                break;
            case 1:
                dirx = -1;
                diry = 0;
                break;
            case 2:
                dirx = 0;
                diry = -1;
                break;
            case 3:
                dirx = 1;
                diry = 0;
                break;

        }
        boolean flag=true;
        for (int i = 0; i <= sight &&flag; i++) {
            //for (int j = -i; j <= i; j++) {
            int fovx = thiefCurrentPosition.getX() + dirx * i  ;//*j
            int fovy = thiefCurrentPosition.getY() + diry * i ;//* j
            Position possiblyCanSee = new Position(fovx, fovy);

            if (isntWall(possiblyCanSee)) {
                kill_range.add(possiblyCanSee);
            } else {
                flag = false;
                break;
                //}
            }
        }
        return kill_range;
    }
    public List<Prize> getPrize() {
        return prize;
    }

    public void setPrize(List<Prize> prize) {
        this.prize = prize;
    }
    public boolean pickedupPrize(Position position) {
        if (getPrize().getFirst().getPosition().equals(position)){
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

// fazerrrrrrrrrr
    public Position[] camFieldVision(Camera camera) {

    }
}


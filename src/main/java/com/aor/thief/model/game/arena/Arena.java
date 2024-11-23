package com.aor.thief.model.game.arena;

import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Prize;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;
import com.aor.thief.model.game.menu.Position;

import java.util.List;

public class Arena {
    private final int width;
    private final int height;
    private Thief thief;
    private Prize prize;

    private List<Guard> guards;
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

    public void setHero(Thief thief) {
        this.thief = thief;
    }

    public List<Guard> getMonsters() {
        return guards;
    }

    public void setMonsters(List<Guard> guards) {
        this.guards = guards;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    public boolean isGuard(Position position) { //Maybe wont be necessary, guards will have to check the space infront, temos de falar decidir quanto vem, se só para a frente etc.
        for (Guard guard : guards)
            if (guard.getPosition().equals(position))
                return true;
        return false;
    }
}


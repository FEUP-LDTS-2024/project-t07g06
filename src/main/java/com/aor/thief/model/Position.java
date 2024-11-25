package com.aor.thief.model;

import com.aor.thief.model.game.elements.Element;

public class Position  {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getLeft() {
        return new Position(x - 1, y);
    }

    public Position getRight() {
        return new Position(x + 1, y);
    }

    public Position getUp() {
        return new Position(x, y - 1);
    }

    public Position getDown() {
        return new Position(x, y + 1);
    }

    public Position movingpattern(int direction){
        switch (direction){
            case(0)://down
                return new Position(x, y + 1);
            case(1)://left
                return new Position(x - 1, y);
            case(2)://up
                return new Position(x, y - 1);
            case(3)://right
                return new Position(x + 1, y);
            default:
                return new Position(x, y);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }
}

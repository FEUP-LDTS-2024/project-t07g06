package com.aor.thief.model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PositionTest {
    @Property
    void getLeft(@ForAll int x, @ForAll int y) {
        assertEquals(x - 1, new Position(x, y).getLeft().getX());
        assertEquals(y, new Position(x, y).getLeft().getY());
    }

    @Property
    void getRight(@ForAll int x, @ForAll int y) {
        assertEquals(x + 1, new Position(x, y).getRight().getX());
        assertEquals(y, new Position(x, y).getRight().getY());
    }

    @Property
    void getUp(@ForAll int x, @ForAll int y) {
        assertEquals(x, new Position(x, y).getUp().getX());
        assertEquals(y - 1, new Position(x, y).getUp().getY());
    }

    @Property
    void getDown(@ForAll int x, @ForAll int y) {
        assertEquals(x, new Position(x, y).getDown().getX());
        assertEquals(y + 1, new Position(x, y).getDown().getY());
    }

    @Property
    void movingPatternDown(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position result = position.movingpattern(0);

                assertEquals(x, result.getX());
                assertEquals(y + 1, result.getY());
    }

    @Property
    void movingPatternLeft(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position result = position.movingpattern(1);

        assertEquals(x - 1, result.getX());
        assertEquals(y, result.getY());
    }

    @Property
    void movingPatternRight(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position result = position.movingpattern(3);

        assertEquals(x + 1, result.getX());
        assertEquals(y, result.getY());
    }

    @Property
    void movingPatternUp(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position result = position.movingpattern(2);

        assertEquals(x, result.getX());
        assertEquals(y - 1, result.getY());
    }

    @Property
    void distance(@ForAll int x1, @ForAll int y1, @ForAll int x2, @ForAll int y2) {
        Position p1 = new Position(x1, y1);
        Position p2 = new Position(x2, y2);
        int expectedDistance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        assertEquals(expectedDistance, p1.distance(p2));
    }

    @Property
    void updateHitbox(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        List<Position> hitbox = position.updatehitbox_();

        assertEquals(9, hitbox.size());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(hitbox.contains(new Position(x + i, y + j)));
            }
        }
    }

    @Property
    void getNeighbors(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        List<Position> neighbors = position.getNeighbors();

        assertEquals(4, neighbors.size());
        assertTrue(neighbors.contains(new Position(x - 1, y)));
        assertTrue(neighbors.contains(new Position(x + 1, y)));
        assertTrue(neighbors.contains(new Position(x, y - 1)));
        assertTrue(neighbors.contains(new Position(x, y + 1)));
    }

}

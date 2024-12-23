package com.aor.thief.controller;

import com.aor.thief.controller.game.ThiefController;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ThiefControllerTest {
    private ThiefController controller;
    private Thief thief;
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new Arena(40, 40);
        thief = new Thief(5, 5, 1, 0, new Position(5, 5).updatehitbox_(), 0, "");
        arena.setThief(thief);
        arena.setWalls(new ArrayList<>());
        arena.setGuards(new ArrayList<>());
        arena.setPrize(new ArrayList<>());
        arena.setDeads(new ArrayList<>());
        arena.setDoor(new Door(5, 5, new Position(5,5).updatehitbox_()));

        controller = new ThiefController(arena);
    }

    @Test
    void moveThiefRightEmpty() {
        controller.moveThiefRight();
        assertEquals(new Position(6, 5), thief.getPosition());
    }

    @Test
    void moveHeroRightNotEmpty() {
        arena.setWalls(Arrays.asList(new Wall(10, 5, new Position(10,5).updatehitbox_())));
        controller.moveThiefRight();
        assertEquals(new Position(5, 5), thief.getPosition());
    }

    @Test
    void moveThiefLeftEmpty() {
        controller.moveThiefLeft();
        assertEquals(new Position(4, 5), thief.getPosition());
    }

    @Test
    void moveHeroLeftNotEmpty() {
        thief.setPosition(new Position(15,5));
        arena.setWalls(Arrays.asList(new Wall(10, 5, new Position(10,5).updatehitbox_())));
        controller.moveThiefLeft();
        assertEquals(new Position(15, 5), thief.getPosition());
    }

    @Test
    void moveThiefUpEmpty() {
        controller.moveThiefUp();
        assertEquals(new Position(5, 4), thief.getPosition());
    }

    @Test
    void moveThiefUpNotEmpty() {
        thief.setPosition(new Position(5,10));
        arena.setWalls(Arrays.asList(new Wall(5, 5, new Position(5,5).updatehitbox_())));
        controller.moveThiefUp();
        assertEquals(new Position(5, 10), thief.getPosition());
    }

    @Test
    void moveThiefDownEmpty() {
        controller.moveThiefDown();
        assertEquals(new Position(5, 6), thief.getPosition());
    }

    @Test
    void moveHeroDownNotEmpty() {
        arena.setWalls(Arrays.asList(new Wall(5, 10, new Position(5,10).updatehitbox_())));
        controller.moveThiefDown();
        assertEquals(new Position(5, 5), thief.getPosition());
    }

    @Test
    void canThiefEscape() {
        assertTrue(controller.canThiefEscape());
    }

    @Test
    void cantThiefEscape() {
        arena.setPrize(Arrays.asList(new Prize(1, 1, new ArrayList<>())));
        assertFalse(controller.canThiefEscape());
    }

    @Test
    void canKill() throws InterruptedException {
        List<Guard> guards = new ArrayList<>();
        guards.add(new Guard(5, 10, 0, false, new Position(5, 10).updatehitbox_(), 0, ""));
        arena.setGuards(guards);
        assertTrue(controller.canKill());
    }

    @Test
    void cantKill() throws InterruptedException {
        List<Guard> guards = new ArrayList<>();
        arena.setCameras(new ArrayList<>());
        arena.setStillCameras(new ArrayList<>());
        arena.setCops(new ArrayList<>());
        arena.setDeads(new ArrayList<>());
        guards.add(new Guard(8, 10, 0, false, new Position(8, 10).updatehitbox_(), 0, ""));
        arena.setGuards(guards);
        assertFalse(controller.canKill());
    }
}
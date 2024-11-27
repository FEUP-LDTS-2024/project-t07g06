package com.aor.thief.controller;

import com.aor.thief.controller.game.ThiefController;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThiefControllerTest {
    private ThiefController controller;
    private Thief thief;
    private Arena arena;

    @BeforeEach
    void setUp(){
        arena= new Arena(10, 10);

        thief= new Thief(5,5,0,0);
        arena.setThief(thief);

        arena.setWalls(Arrays.asList());
        arena.setGuards(new ArrayList<>());
        arena.setPrize(Arrays.asList());
        arena.setDoor(new Door(5,5));

        controller = new ThiefController(arena);
    }

    @Test
    void moveThiefRightEmpty() {
        controller.moveThiefRight();
        assertEquals(new Position(6, 5), thief.getPosition());
    }

    @Test
    void moveHeroRightNotEmpty() {
        arena.setWalls(Arrays.asList(new Wall(6, 5)));
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
        arena.setWalls(Arrays.asList(new Wall(4, 5)));
        controller.moveThiefLeft();
        assertEquals(new Position(5, 5), thief.getPosition());
    }
    @Test
    void moveThiefUpEmpty() {
        controller.moveThiefUp();
        assertEquals(new Position(5, 4), thief.getPosition());
    }

    @Test
    void moveThiefUpNotEmpty() {
        arena.setWalls(Arrays.asList(new Wall(5, 4)));
        controller.moveThiefUp();
        assertEquals(new Position(5, 5), thief.getPosition());
    }

    @Test
    void moveThiefDownEmpty() {
        controller.moveThiefDown();
        assertEquals(new Position(5, 6), thief.getPosition());
    }

    @Test
    void moveHeroDownNotEmpty() {
        arena.setWalls(Arrays.asList(new Wall(5, 6)));
        controller.moveThiefDown();
        assertEquals(new Position(5, 5), thief.getPosition());
    }


    @Test
    void canThiefEscape() {

        assertEquals(true, controller.canThiefEscape());
    }

    @Test
    void cantThiefEscape() {
        arena.setPrize(Arrays.asList(new Prize(1,1)));
        assertEquals(false, controller.canThiefEscape());
    }

    @Test
    void canKill(){
        List<Guard> guards = new ArrayList<>();
        guards.add(new Guard(5, 6, 0)); // Adicionando um guard
        arena.setGuards(guards);
        assertEquals(true,controller.canKill());
    }

    @Test
    void cantKill(){
        List<Guard> guards = new ArrayList<>();
        guards.add(new Guard(7, 7, 0)); // Adicionando um guard
        arena.setGuards(guards);
        assertEquals(false,controller.canKill());
    }

}
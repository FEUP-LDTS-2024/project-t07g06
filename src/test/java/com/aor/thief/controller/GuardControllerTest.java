package com.aor.thief.controller;

import com.aor.thief.Game;
import com.aor.thief.controller.game.GuardController;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GuardControllerTest {
    private GuardController controller;
    private Thief thief;
    private Arena arena;
    private Game game;

    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10);

        thief = new Thief(6, 5,0,0);
        arena.setThief(thief);

        arena.setWalls(Arrays.asList());
        arena.setGuards(Arrays.asList());

        controller = new GuardController(arena);

        game = Mockito.mock(Game.class);
    }
    @Test
    void moveGuardClosed() throws IOException {
        Guard guard = new Guard(5, 5,0,false );
        arena.setGuards(Arrays.asList(guard));
        arena.setWalls(Arrays.asList(
                new Wall(4, 5),
                new Wall(6, 5),
                new Wall(5, 4),
                new Wall(5, 6)
        ));

        for (int i = 0; i < 10; i++)
            controller.step(game, GUI.ACTION.NONE, 1000);

        assertEquals(new Position(5, 5), guard.getPosition());
    }

    @Test
    void moveGuardsGap() throws IOException {
        Guard guard = new Guard(5, 5,0,false );
        arena.setGuards(Arrays.asList(guard));
        arena.setWalls(Arrays.asList(
                new Wall(5, 4),
                new Wall(4, 5),
                new Wall(5, 6)
        ));

        long time = 0;

        while (guard.getPosition().equals(new Position(5, 5))) {
            time += 500;
            controller.step(game, GUI.ACTION.NONE, time);
        }

        assertEquals(new Position(6, 5), guard.getPosition());
    }

}

package com.aor.thief.controller;

import com.aor.thief.Game;
import com.aor.thief.controller.game.GuardController;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Arrays;

public class GuardControllerTest {
    private GuardController controller;
    private Thief thief;
    private Arena arena;
    private Game game;

    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10);

        thief = new Thief(5, 5,0,0);
        arena.setThief(thief);

        arena.setWalls(Arrays.asList());
        arena.setGuards(Arrays.asList());

        controller = new GuardController(arena);

        game = Mockito.mock(Game.class);
    }
}

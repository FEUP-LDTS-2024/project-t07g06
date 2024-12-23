package com.aor.thief.controller;

import com.aor.thief.Game;
import com.aor.thief.controller.game.*;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.arena.Arena;

import com.aor.thief.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ArenaControllerTest {

    @Mock
    private Arena arena;

    @Mock
    private ArenaController arenaController;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        arenaController = new ArenaController(arena);
        Arena arena = mock(Arena.class);
        ArenaController arenaController = new ArenaController(arena);

    }

    @Test
    public void tconstructor() {


        assertNotNull(arenaController.getThiefController());
        assertNotNull(arenaController.getGuardController());
        assertNotNull(arenaController.getCopController());
        assertNotNull(arenaController.getCameraController());
        assertNotNull(arenaController.getCameraStillController());
    }

    @Test
    public void tStepQuit() throws IOException, InterruptedException {

        Game game = mock(Game.class);
        GUI.ACTION action = GUI.ACTION.QUIT;


        arenaController.step(game, action, 0);


        verify(game).stopWantedSound();

        verify(game).playGameSound();

        verify(game).setState(any(MenuState.class));
    }



    @Test
    public void tStateChangeAction() throws IOException, InterruptedException {

        Game game = mock(Game.class);
        GUI.ACTION action = GUI.ACTION.PICK_UP;


        arenaController.step(game, action, 0);


        verify(game, never()).setState(any(MenuState.class));
    }
}


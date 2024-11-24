package com.aor.thief.states;

import com.aor.thief.controller.Controller;
import com.aor.thief.controller.game.ArenaController;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.viewer.game.GameViewer;
import com.aor.thief.viewer.Viewer;

public class GameState extends State<Arena> {
    public GameState(Arena arena) {
        super(arena);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }

}

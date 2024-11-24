package com.aor.thief.controller.game;

import com.aor.thief.controller.Controller;
import com.aor.thief.model.game.arena.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena) {
        super(arena);
    }
}

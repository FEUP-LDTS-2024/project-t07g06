package com.aor.thief.model.game.arena;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());

        return arena;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();
}

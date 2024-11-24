package com.aor.thief.model.game.arena;

import java.util.List;

import com.aor.thief.model.game.elements.Prize;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Wall;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());
        arena.setGuards(createGuard());
        arena.setThief(createThief());
        arena.setPrize(createPrize());
        arena.setWalls(createWalls());
        return arena;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Wall> createWalls();

    protected abstract List<Guard> createGuard();

    protected abstract Thief createThief();

    protected abstract List<Prize> createPrize();

}

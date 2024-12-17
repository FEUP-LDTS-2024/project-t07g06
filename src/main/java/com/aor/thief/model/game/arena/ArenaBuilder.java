package com.aor.thief.model.game.arena;

import java.util.List;

import com.aor.thief.model.game.elements.*;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());
        arena.setGuards(createGuard());
        arena.setThief(createThief());
        arena.setPrize(createPrize());
        arena.setWalls(createWalls());
        arena.setDoor(createDoor());
        arena.setCameras(createCamera());
        arena.setDeads(createDeads());
        arena.setCops(createCop());
        return arena;
    }


    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Wall> createWalls();

    protected abstract List<DeadBody> createDeads();

    protected abstract List<Guard> createGuard();

    protected abstract Thief createThief();

    protected abstract List<Prize> createPrize();

    protected abstract Door createDoor();

    protected abstract List<Camera> createCamera();

    protected abstract List<Cop> createCop();

}


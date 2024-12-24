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
        arena.setStillCameras(createStillCameras());
        return arena;
    }


    public abstract int getWidth();

    public abstract int getHeight();

    public abstract List<Wall> createWalls();

    public abstract List<DeadBody> createDeads();

    public abstract List<Guard> createGuard();

    public  abstract Thief createThief();

    public  abstract List<Prize> createPrize();

    public  abstract Door createDoor();

    public  abstract List<Camera> createCamera();

    public  abstract List<CameraStill> createStillCameras();

    public  abstract List<Cop> createCop();

}


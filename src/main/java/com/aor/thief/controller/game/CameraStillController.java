package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.model.game.elements.CameraStill;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.Guard;

import java.io.IOException;

public class CameraStillController extends GameController {
    private long lastMovement;

    public CameraStillController(Arena arena) {
        super(arena);
    }
    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if(!getModel().getStillCameras().isEmpty()) {
            if (!getModel().getStillCameras().getFirst().isFound()) {
                for (CameraStill camera : getModel().getCamera()) {
                    if (camera.getCorner().isEmpty()) {
                        getModel().defAxis(camera);
                    }
                    getModel().fov(camera);
                }
            }
            if (isCaught()) {
                game.stopGameSound();game.playWantedSound();
                for (Guard guard : getModel().getGuards()) {
                    guard.setFound(true);
                }
                for (Cop cop : getModel().getCops()) {
                    cop.setFound(true);
                }
            }
        }

    }


    public boolean isCaught() {
        for (CameraStill camera : getModel().getStillCameras()) {
            for (Position caught : getModel().fov(camera)) {
                if (getModel().getThief().getHitbox().contains(caught)) {
                    return true;
                }
            }

        }
        return false;
    }

}

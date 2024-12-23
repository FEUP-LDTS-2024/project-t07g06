package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.controller.Controller;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.CameraStill;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.MenuState;

import java.io.IOException;
import java.util.List;

public class ArenaController extends Controller {
    private final ThiefController thiefController;
    private final GuardController guardController;
    private final CopController copController;
    private final CameraStillController cameraStillController;

    private final CameraController cameraController;
    public ArenaController(Arena arena) {
        super(arena);
        this.thiefController = new ThiefController(arena);
        this.guardController = new GuardController(arena);

        this.copController = new CopController(arena);

        this.cameraController = new CameraController(arena);

        this.cameraStillController=  new CameraStillController(arena);

    }

    public void step(Game game, GUI.ACTION action,long time) throws IOException, InterruptedException {
        if (action == GUI.ACTION.QUIT)
        {game.stopWantedSound();game.playGameSound();
            game.setState(new MenuState(new Menu()));}
        else {
            thiefController.step(game, action, time);
            guardController.step(game, action,time);

            copController.step(game, action, time);

            cameraController.step(game, action,time);

            cameraStillController.step(game, action,time);
        }
    }

    public ThiefController getThiefController() { return thiefController;}
    public GuardController getGuardController() { return guardController;}
    public CopController getCopController() { return copController;}
    public CameraStillController getCameraStillController() { return cameraStillController;}
    public CameraController getCameraController() { return cameraController;}

}

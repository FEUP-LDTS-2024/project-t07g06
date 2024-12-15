package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.MenuState;

import java.io.IOException;

public class CameraController extends GameController {
    private long lastMove;
    private int cur_direc;



public CameraController(Arena arena){
    super(arena);
    this.lastMove=0;
    this.cur_direc=0;
}

//????
    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

    }


    private boolean isSeen() {
        for (Camera camera : getModel().getCamera()) {
            for (Position caught : getModel().camFieldVision(camera)){
                if (caught.equals(getModel().getThief().getPosition())){return true;}
            }
        }
        return false;
    }

    //fazerrrrr
    private void moveCam(int index, Position position) {

        }
    }





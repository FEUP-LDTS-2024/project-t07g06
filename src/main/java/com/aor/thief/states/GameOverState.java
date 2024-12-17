package com.aor.thief.states;

import com.aor.thief.controller.Controller;
import com.aor.thief.controller.gameover.GameOverController;
import com.aor.thief.model.gameover.GameOver;
import com.aor.thief.viewer.Viewer;
import com.aor.thief.viewer.gameover.GameOverViewer;

public class GameOverState extends State<GameOver>{
    public GameOverState(GameOver model) {super(model);}

    @Override
    protected Viewer<GameOver> getViewer() {
        return new GameOverViewer(getModel());
    }

    @Override
    protected Controller<GameOver> getController() {
        return new GameOverController(getModel());
    }

}






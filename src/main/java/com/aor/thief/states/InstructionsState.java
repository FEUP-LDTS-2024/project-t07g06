package com.aor.thief.states;

import com.aor.thief.controller.Controller;
import com.aor.thief.controller.gameover.GameOverController;
import com.aor.thief.controller.instructions.InstructionsController;
import com.aor.thief.model.gameover.GameOver;
import com.aor.thief.model.instructions.Instructions;
import com.aor.thief.viewer.Viewer;
import com.aor.thief.viewer.gameover.GameOverViewer;
import com.aor.thief.viewer.instructions.InstructionsViewer;

public class InstructionsState extends State<Instructions> {

public InstructionsState(Instructions model){super(model);}

@Override
protected Viewer<Instructions> getViewer() {return new InstructionsViewer(getModel());
}

@Override
protected Controller<Instructions> getController() {return new InstructionsController(getModel());}

}





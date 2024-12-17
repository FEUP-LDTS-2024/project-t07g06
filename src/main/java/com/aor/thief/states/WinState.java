package com.aor.thief.states;

import com.aor.thief.controller.Controller;
import com.aor.thief.controller.win.WinController;
import com.aor.thief.model.win.Win;
import com.aor.thief.viewer.Viewer;
import com.aor.thief.viewer.win.WinViewer;

public class WinState extends State<Win>{
    public WinState(Win model) {super(model);}

    @Override
    protected Viewer<Win> getViewer() {
        return new WinViewer(getModel());
    }

    @Override
    protected Controller<Win> getController() {
        return new WinController(getModel());
    }

}






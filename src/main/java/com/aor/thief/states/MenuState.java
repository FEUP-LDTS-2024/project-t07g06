package com.aor.thief.states;

import com.aor.thief.controller.Controller;
import com.aor.thief.controller.menu.MenuController;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.viewer.Viewer;
import com.aor.thief.viewer.menu.MenuViewer;

public class MenuState extends State<Menu>{
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }

}

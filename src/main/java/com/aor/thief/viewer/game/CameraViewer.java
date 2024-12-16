package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Camera;

public class CameraViewer implements ElementViewer<Camera> {
    @Override
    public void draw(Camera camera, GUI gui) {gui.drawCamera(camera.getPosition());
    }
}


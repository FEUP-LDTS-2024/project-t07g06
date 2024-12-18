package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.model.game.elements.CameraStill;

public class CameraStillViewer implements ElementViewer<CameraStill>{
    @Override
    public void draw(CameraStill camera, GUI gui) {
        gui.drawStillCamera(camera.getPosition());
    }
}

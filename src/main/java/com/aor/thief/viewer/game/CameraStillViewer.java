package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.model.game.elements.CameraStill;

import java.io.IOException;

public class CameraStillViewer implements ElementViewer<CameraStill>{
    @Override
    public void draw(CameraStill camera, GUI gui) throws IOException {
        gui.drawImage(camera.getPosition(),"src/main/resources/sprites/camera/up_left_up.png");
    }
}

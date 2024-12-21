package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.model.game.elements.CameraStill;

import java.io.IOException;

public class CameraStillViewer implements ElementViewer<CameraStill>{
    @Override
    public void draw(CameraStill camera, GUI gui) throws IOException {
        if(!camera.isFound()) {
            for (Position position : camera.getFov()) {
                gui.drawImage(position, "src/main/resources/sprites/vision/pixil-frame-0.png");
            }
        }
        gui.drawImage(camera.getPosition(),"src/main/resources/sprites/camera/up_left_up1.png");

    }
}

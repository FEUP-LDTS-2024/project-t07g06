package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.model.game.elements.CameraStill;

import java.io.IOException;

public class CameraStillViewer implements ElementViewer<CameraStill>{
    private final String path ="src/main/resources/sprites/camera/";
    @Override
    public void draw(CameraStill camera, GUI gui) throws IOException {
        if(!camera.isFound()) {
            for (Position position : camera.getFov()) {
                gui.drawImage(position, "src/main/resources/sprites/vision/pixil-frame-0.png");
            }
        }
        switch(camera.getDirection()){
            case(0):
                gui.drawImage(camera.getPosition(),path +"up_left_down.png");
                break;
            case(1):
                gui.drawImage(camera.getPosition(),path +"up_right_up.png");
                break;
            case(2):
                gui.drawImage(camera.getPosition(),path +"down_right_down.png");
                break;
            case(3):
                gui.drawImage(camera.getPosition(),path +"up_left_up.png");
                break;
        }


    }
}

package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Camera;
import com.aor.thief.model.game.elements.Guard;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CameraViewer implements ElementViewer<Camera> {
    private final String path = "src/main/resources/sprites/camera/";
    @Override
    public void draw(Camera camera, GUI gui) throws IOException {
        if(camera.getDraw().isEmpty()){
            camera.setDraw(getCorner(camera) + "_mid.png");}
        if(!camera.isFound()) {
            for (Position position : camera.getFov()) {
                gui.drawImage(position, "src/main/resources/sprites/vision/pixil-frame-0.png");
            }
        }
        gui.drawImage(camera.getPosition(), path + camera.getDraw());
    }

    public String getCorner(Camera camera) {
        List<Integer> corner = camera.getCorner();
        List<Integer> up_right =  Arrays.asList(0,10,1);
        List<Integer> up_left =  Arrays.asList(0,30,3);
        List<Integer> down_left =  Arrays.asList(2,32,3);
        List<Integer> down_right =  Arrays.asList(2,12,1);
        if(corner.equals(up_right)){return "up_right";}
        else if(corner.equals(up_left)){return "up_left";}
        else if(corner.equals(down_left)){return "down_left";}
        else if(corner.equals(down_right)){return"down_right";}
        return "";
    }
}


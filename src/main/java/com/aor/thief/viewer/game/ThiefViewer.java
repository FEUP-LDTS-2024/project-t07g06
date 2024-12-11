package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;

public class ThiefViewer implements ElementViewer<Thief> {
    @Override
    public void draw(Thief thief, GUI gui) {
        switch (thief.getDirection()){
            case(0):
                //ainda depois vai ser preciso para os passos, thread como o outro
                gui.drawImage(thief.getPosition(),"down");
                if(gui.getNextAction()==ACTION.KILL) {
                    //have to include a thread so that it will draw each image timed
                    gui.drawImage(thief.getPosition(),"down_shoting");
                }
                break;
            case(1):
                gui.drawImage(thief.getPosition(),"left");
                if(gui.getNextAction()==ACTION.KILL) {
                    //have to include a thread so that it will draw each image timed
                    gui.drawImage(thief.getPosition(), "left_shoting");
                }
                break;
            case(2):
                gui.drawImage(thief.getPosition(),"up");
                if(gui.getNextAction()==ACTION.KILL) {
                    //have to include a thread so that it will draw each image timed
                    gui.drawImage(thief.getPosition(), "up_shoting");
                }
                break;
            case(3):
                gui.drawImage(thief.getPosition(),"right");
                if(gui.getNextAction()==ACTION.KILL) {
                    //have to include a thread so that it will draw each image timed
                    gui.drawImage(thief.getPosition(), "right_shoting");
                }
                break;
        }
    }
}

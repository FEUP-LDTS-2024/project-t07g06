package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.game.elements.Wall;

public class ThiefViewer implements ElementViewer<Thief> {
    private int feet=0; //tentar resolver, não sei se vai, se calhar meter o pé onde se chama o thiefviewer
    @Override
    public void draw(Thief thief, GUI gui) throws InterruptedException {
        //de momento sempre que se chamar ele vai desenhar o caso 0 porque isto aqui sempre que
        switch (thief.getDirection()){ //cria o draw vai estar a zero, shit :(
            case(0):
                switch(feet){
                    case(0):
                        gui.drawImage(thief.getPosition(),"down_step_right");
                        this.feet++;
                        break;
                    case(1):
                        gui.drawImage(thief.getPosition(),"down_step_left");
                        feet--;
                        break;
                }
                if(gui.getNextAction()==ACTION.KILL) {
                    gui.drawImage(thief.getPosition(),"down_shoting");
                    Thread.sleep(1000);
                    gui.drawImage(thief.getPosition(),"down");
                }
                break;
            case(1):
                switch(feet){
                    case(0):
                        gui.drawImage(thief.getPosition(),"left_step_right");
                        this.feet++;
                        break;
                    case(1):
                        gui.drawImage(thief.getPosition(),"left_step_left");
                        feet--;
                        break;
                }
                if(gui.getNextAction()==ACTION.KILL) {
                    //have to include a thread so that it will draw each image timed
                    gui.drawImage(thief.getPosition(), "left_shoting");
                    Thread.sleep(1000);
                    gui.drawImage(thief.getPosition(),"left");
                }
                break;
            case(2):
                switch(feet){
                    case(0):
                        gui.drawImage(thief.getPosition(),"up_step_right");
                        this.feet++;
                        break;
                    case(1):
                        gui.drawImage(thief.getPosition(),"up_step_left");
                        feet--;
                        break;
                }
                if(gui.getNextAction()==ACTION.KILL) {
                    //have to include a thread so that it will draw each image timed
                    gui.drawImage(thief.getPosition(), "up_shoting");
                    Thread.sleep(1000);
                    gui.drawImage(thief.getPosition(),"up");
                }
                break;
            case(3):
                switch(feet){
                    case(0):
                        gui.drawImage(thief.getPosition(),"right_step_right");
                        this.feet++;
                        break;
                    case(1):
                        gui.drawImage(thief.getPosition(),"right_step_left");
                        feet--;
                        break;
                }
                if(gui.getNextAction()==ACTION.KILL) {
                    //have to include a thread so that it will draw each image timed
                    gui.drawImage(thief.getPosition(), "right_shoting");
                    Thread.sleep(1000);
                    gui.drawImage(thief.getPosition(),"right");
                }
                break;
        }
    }
}

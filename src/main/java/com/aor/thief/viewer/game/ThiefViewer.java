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
                        gui.drawImage(thief.getPosition(),getClass().getClassLoader().getResource("/resouces/sprites/p-pernadireita-baixo.png").toExternalForm());
                        this.feet++;
                        break;
                    case(1):
                        gui.drawImage(thief.getPosition(),getClass().getClassLoader().getResource("/resouces/sprites/p-pernaesquerda-baixo.png").toExternalForm());
                        feet--;
                        break;
                }
                if(gui.getNextAction()==GUI.ACTION.KILL) {
                    gui.drawImage(thief.getPosition(),getClass().getClassLoader().getResource("/resouces/sprites/p-disparar-baixo.png").toExternalForm());
                    Thread.sleep(1000);
                    gui.drawImage(thief.getPosition(),getClass().getClassLoader().getResource("/resouces/sprites/p-parado-baixo.png").toExternalForm());
                }
                break;
            case(1):
                switch(feet){
                    case(0):
                        gui.drawImage(thief.getPosition(),getClass().getClassLoader().getResource("/resouces/sprites/p-pernadireita-lado-esquerdo.png").toExternalForm());
                        this.feet++;
                        break;
                    case(1):
                        gui.drawImage(thief.getPosition(),getClass().getClassLoader().getResource("/resouces/sprites/p-pernaesquerda-lado-esquerdo.png").toExternalForm());
                        feet--;
                        break;
                }
                if(gui.getNextAction()==GUI.ACTION.KILL) {
                    //have to include a thread so that it will draw each image timed
                    gui.drawImage(thief.getPosition(), getClass().getClassLoader().getResource("/resouces/sprites/p-disparar-lado-esquerdo.png").toExternalForm());
                    Thread.sleep(1000);
                    gui.drawImage(thief.getPosition(),getClass().getClassLoader().getResource("/resouces/sprites/p-parado-ladoe-squerdo.png").toExternalForm());
                }
                break;
            case(2):
                switch(feet){
                    case(0):
                        gui.drawImage(thief.getPosition(),getClass().getClassLoader().getResource("/resouces/sprites/p-pernadireita-cima.png").toExternalForm());
                        this.feet++;
                        break;
                    case(1):
                        gui.drawImage(thief.getPosition(),getClass().getClassLoader().getResource("/resouces/sprites/p-pernaesquerda-cima.png").toExternalForm());
                        feet--;
                        break;
                }
                if(gui.getNextAction()==GUI.ACTION.KILL) {
                    //have to include a thread so that it will draw each image timed
                    gui.drawImage(thief.getPosition(), getClass().getClassLoader().getResource("/resouces/sprites/p-disparar-cima.png").toExternalForm());
                    Thread.sleep(1000);
                    gui.drawImage(thief.getPosition(),getClass().getClassLoader().getResource("/resouces/sprites/p-parado-cima.png").toExternalForm());
                }
                break;
            case(3):
                switch(feet){
                    case(0):
                        gui.drawImage(thief.getPosition(),"/resouces/sprites/p-pernadireita-ladodireito.png");
                        this.feet++;
                        break;
                    case(1):
                        gui.drawImage(thief.getPosition(),"/resouces/sprites/p-pernaesquerda-ladodireito.png");
                        feet--;
                        break;
                }
                if(gui.getNextAction()==GUI.ACTION.KILL) {
                    //have to include a thread so that it will draw each image timed
                    gui.drawImage(thief.getPosition(), "/resouces/sprites/p-disparar-ladodireito.png");
                    Thread.sleep(1000);
                    gui.drawImage(thief.getPosition(),"/resouces/sprites/p-parado-ladodireito.png");
                }
                break;
        }
    }
}

package com.aor.thief;
import com.aor.thief.gui.LanternaGUI;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.sound.*;
import com.aor.thief.states.MenuState;
import com.aor.thief.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;
    private final LoopedSoundPlayer loopedSoundPlayer;
    private final InstanceSound itemPickupSound;
    private final LoopedSoundPlayer win;
    private final LoopedSoundPlayer lossSound;
    private final LoopedSoundPlayer wanted;






    public Game() throws FontFormatException, IOException, URISyntaxException,Exception {
        this.gui = new LanternaGUI(160, 90);

        this.state = new MenuState(new Menu());
        this.loopedSoundPlayer = new SoundFactory().createBackgroundSound();
        this.itemPickupSound= new SoundFactory().createItemPickupSound();
        this.win= new SoundFactory().createWinSound();
        this.lossSound=new SoundFactory().createLoseSound();
        this.wanted=new SoundFactory().createWantedSound();

    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException,Exception {

        new Game().start();

    }

    public void setState(State state) {
        this.state = state;
    }


    public  void playFootStepSound() { //como footstep é chamado várias vezes num curto espaço de tempo,é melhor criar uma nova instância de footstep a cada utilização
        InstanceSound footStepSound = new SoundFactory().createFootStepSound();
        footStepSound.start();
    }

    public  void playGunShot() {
        InstanceSound gunShot = new SoundFactory().createGunShotSound();
        gunShot.start();
    }

    public  void playGuardDeathSound() {
        InstanceSound guardDeathSound = new SoundFactory().createGuardDeathSound();
        guardDeathSound.start();
    }

    public void playWantedSound(){
        wanted.start();
    }

    public void stopWantedSound(){
        wanted.stop();
    }

    public void playLossSound(){
        lossSound.start();
    }

    public void stopLossSound(){
        lossSound.stop();
    }

    public void playGameSound(){
        loopedSoundPlayer.start();
    }

    public void stopGameSound(){
        loopedSoundPlayer.stop();
    }


    public void playItemPickupSound(){
        itemPickupSound.start();
    }

    public void playMenuChange(){
        InstanceSound menuChange = new SoundFactory().createMenuChangeSound();
        menuChange.start();
    }

    public void playMenuSelect(){
        InstanceSound menuSelect = new SoundFactory().createMenuSelectSound();
        menuSelect.start();
    }


    public void playWin(){
        win.start();
    }

    public void stopWin(){
        win.stop();
    }



    private void start() throws IOException ,InterruptedException{

        int FPS = 30;
        int frameTime = 1000 / FPS;
        playGameSound();
        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
        gui.close();
    }
}


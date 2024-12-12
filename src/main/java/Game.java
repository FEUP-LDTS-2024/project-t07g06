package com.aor.thief;
import com.aor.thief.gui.LanternaGUI;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.sound.*;
import com.aor.thief.states.MenuState;
import com.aor.thief.states.State;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Game {
    private final LanternaGUI gui;
    private State state;
    private final BackgroundSoundPlayer backgroundSoundPlayer;
    private final ItemPickupSound itemPickupSound;


    public Game() throws FontFormatException, IOException, URISyntaxException,Exception {
        this.gui = new LanternaGUI(35, 20);
        this.state = new MenuState(new Menu()); //objetivo:Aqui ler o menu, para depois o menu lançar o nivel
        this.backgroundSoundPlayer = new BackgroundSoundPlayer(new SoundLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/background.wav"))), AudioSystem.getClip()));
        this.itemPickupSound= new ItemPickupSound(new SoundLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/itempickup.wav"))), AudioSystem.getClip()));
    }
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException,Exception {
        new Game().start();

    }

    public void setState(State state) {
        this.state = state;
    }


    public synchronized void playFootStepSound() { //como footstep é chamado várias vezes num curto espaço de tempo,é melhor criar uma nova instância de footstep a cada utilização
        try {
            FootStepSound footStepSound = new FootStepSound(new SoundLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/footstep.wav"))), AudioSystem.getClip()));
            footStepSound.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void playGunShot() {
        try {
            GunShot gunShot = new GunShot(new SoundLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/gunshot.wav"))), AudioSystem.getClip()));
            gunShot.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void playGuardDeathSound() {
        try {
            GuardDeathSoundPlayer guardDeathSoundPlayer = new GuardDeathSoundPlayer(new SoundLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/guarddeathsound.wav"))), AudioSystem.getClip()));
            guardDeathSoundPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void playItemPickupSound(){
        itemPickupSound.start();
    }



    private void start() throws IOException {
        int FPS = 30;
        int frameTime = 1000 / FPS;
        backgroundSoundPlayer.start();
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


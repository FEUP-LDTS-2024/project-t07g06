package com.aor.thief;
import com.aor.thief.gui.LanternaGUI;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.sound.BackgroundSoundPlayer;
import com.aor.thief.sound.GuardDeathSoundPlayer;
import com.aor.thief.sound.SoundLoader;
import com.aor.thief.states.MenuState;
import com.aor.thief.states.State;

import javax.sound.sampled.AudioSystem;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Game {
    private final LanternaGUI gui;
    private State state;
    private final BackgroundSoundPlayer backgroundSoundPlayer;



    public Game() throws FontFormatException, IOException, URISyntaxException,Exception {
        this.gui = new LanternaGUI(35, 20);
        this.state = new MenuState(new Menu()); //objetivo:Aqui ler o menu, para depois o menu lançar o nivel
        this.backgroundSoundPlayer = new BackgroundSoundPlayer(new SoundLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/background.wav"))), AudioSystem.getClip()));


    }
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException,Exception {
        new Game().start();

    }

    public void setState(State state) {
        this.state = state;
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


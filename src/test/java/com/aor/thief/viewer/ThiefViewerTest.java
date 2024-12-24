package com.aor.thief.viewer;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.viewer.game.ThiefViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThiefViewerTest {
    private Thief thief;
    private ThiefViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        thief = new Thief(10, 10, 0, 0, new Position(10, 10).updatehitbox_(), 0, "");
        viewer = new ThiefViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawIsEmpty() throws IOException, InterruptedException {
        thief.setDraw("");
        viewer.draw(thief, gui);
        Mockito.verify(gui, Mockito.times(1)).drawImage(
                thief.getPosition(),
                "src/main/resources/sprites/thief/still_down1.png"
        );
    }

    @Test
    void drawIsnttEmpty() throws IOException, InterruptedException {
        thief.setDraw("custom_image.png");
        viewer.draw(thief, gui);
        Mockito.verify(gui, Mockito.times(1)).drawImage(
                thief.getPosition(),
                "src/main/resources/sprites/thief/custom_image.png"
        );
    }

    @Test
    void getDir() {
        thief.setDirection(0);
        assertEquals("down1", viewer.getDirection(thief));

        thief.setDirection(1);
        assertEquals("left1", viewer.getDirection(thief));

        thief.setDirection(2);
        assertEquals("up1", viewer.getDirection(thief));

        thief.setDirection(3);
        assertEquals("right1", viewer.getDirection(thief));

        thief.setDirection(99);
        assertEquals("down", viewer.getDirection(thief));
    }
}

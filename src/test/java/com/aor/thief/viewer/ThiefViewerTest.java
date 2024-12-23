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
    void drawElement_WhenDrawIsEmpty() throws IOException, InterruptedException {
        // Verifica o caminho quando draw está vazio
        thief.setDraw(""); // Garante que a condição isEmpty será verdadeira
        viewer.draw(thief, gui);
        Mockito.verify(gui, Mockito.times(1)).drawImage(
                thief.getPosition(),
                "src/main/resources/sprites/thief/still_down1.png"
        );
    }

    @Test
    void drawElement_WhenDrawIsNotEmpty() throws IOException, InterruptedException {
        // Verifica o caminho quando draw não está vazio
        thief.setDraw("custom_image.png");
        viewer.draw(thief, gui);
        Mockito.verify(gui, Mockito.times(1)).drawImage(
                thief.getPosition(),
                "src/main/resources/sprites/thief/custom_image.png"
        );
    }

    @Test
    void getDirection_AllCases() {
        // Testa cada direção possível
        thief.setDirection(0);
        Assertions.assertEquals("down1", viewer.getDirection(thief));

        thief.setDirection(1);
        Assertions.assertEquals("left1", viewer.getDirection(thief));

        thief.setDirection(2);
        Assertions.assertEquals("up1", viewer.getDirection(thief));

        thief.setDirection(3);
        Assertions.assertEquals("right1", viewer.getDirection(thief));

        thief.setDirection(99); // Caso padrão
        Assertions.assertEquals("down", viewer.getDirection(thief));
    }
}

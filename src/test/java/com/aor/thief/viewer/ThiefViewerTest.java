package com.aor.thief.viewer;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.viewer.game.ThiefViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ThiefViewerTest {
    private Thief thief;
    private ThiefViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        thief = new Thief(10, 10,0,0,0);
        viewer = new ThiefViewer();
        gui = Mockito.mock(GUI.class);
    }
    @Test
    void drawElement() {
        viewer.draw(thief, gui);
        Mockito.verify(gui, Mockito.times(1)).drawThief(thief.getPosition());
    }
}

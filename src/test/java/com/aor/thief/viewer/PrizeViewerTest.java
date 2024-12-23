package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.Prize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.mockito.Mockito.*;

class PrizeViewerTest {

    private GUI gui;
    private Prize prize;
    private PrizeViewer viewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        prize = mock(Prize.class);
        viewer = new PrizeViewer();
    }


    @Test
    void testDraw_Prize() throws IOException {

        when(prize.getPosition()).thenReturn(new Position(7, 7));
        viewer.draw(prize, gui);
        verify(gui).drawImage(new Position(7, 7), "src/main/resources/sprites/prize/prize_1.png");
    }
}

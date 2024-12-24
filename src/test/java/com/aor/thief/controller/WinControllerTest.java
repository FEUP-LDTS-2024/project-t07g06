import com.aor.thief.Game;
import com.aor.thief.controller.win.WinController;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.win.Win;
import com.aor.thief.states.GameState;
import com.aor.thief.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class WinControllerTest {
    private WinController controller;
    private Win winMock;
    private Game gameMock;

    @BeforeEach
    void setUp() {
        winMock = mock(Win.class);
        gameMock = mock(Game.class);
        controller = new WinController(winMock);
    }

    @Test
    void stepUpAction() throws IOException {
        controller.step(gameMock, GUI.ACTION.UP, 0);

        verify(winMock).previousEntry();
        verify(gameMock).playMenuChange();
        verify(gameMock).stopGameSound();
        verify(gameMock).stopWantedSound();
    }

    @Test
    void stepDownAction() throws IOException {
        controller.step(gameMock, GUI.ACTION.DOWN, 0);

        verify(winMock).nextEntry();
        verify(gameMock).playMenuChange();
        verify(gameMock).stopGameSound();
        verify(gameMock).stopWantedSound();
    }

    @Test
    void stepSelectActionExit() throws IOException {
        when(winMock.isSelectedExit()).thenReturn(true);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).stopWin();
        verify(gameMock).playGameSound();
        verify(gameMock).playMenuSelect();
        verify(gameMock).setState(any(MenuState.class));
    }

    @Test
    void stepSelectActionStart() throws IOException {
        when(winMock.isSelectedStart()).thenReturn(true);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).stopWin();
        verify(gameMock).playGameSound();
        verify(gameMock).playMenuSelect();
        verify(gameMock).setState(any(GameState.class));
    }

    @Test
    void stepSelectActionNoSelection() throws IOException {
        when(winMock.isSelectedExit()).thenReturn(false);
        when(winMock.isSelectedStart()).thenReturn(false);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).stopWin();
        verify(gameMock).playGameSound();
        verify(gameMock, never()).playMenuSelect();
        verify(gameMock, never()).setState(any());
    }
}

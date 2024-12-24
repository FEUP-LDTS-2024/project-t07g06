import com.aor.thief.Game;
import com.aor.thief.controller.gameover.GameOverController;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.gameover.GameOver;
import com.aor.thief.states.GameState;
import com.aor.thief.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameOverControllerTest {
    private GameOverController controller;
    private GameOver gameOverMock;
    private Game gameMock;

    @BeforeEach
    void setUp() {
        gameOverMock = mock(GameOver.class);
        gameMock = mock(Game.class);
        controller = new GameOverController(gameOverMock);
    }

    @Test
    void stepUpAction() throws IOException {
        controller.step(gameMock, GUI.ACTION.UP, 0);

        verify(gameMock).stopGameSound();
        verify(gameMock).stopWantedSound();
        verify(gameOverMock).previousEntry();
        verify(gameMock).playMenuChange();
    }

    @Test
    void stepDownAction() throws IOException {
        controller.step(gameMock, GUI.ACTION.DOWN, 0);

        verify(gameMock).stopGameSound();
        verify(gameMock).stopWantedSound();
        verify(gameOverMock).nextEntry();
        verify(gameMock).playMenuChange();
    }

    @Test
    void stepSelectActionExit() throws IOException {
        when(gameOverMock.isSelectedExit()).thenReturn(true);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).stopLossSound();
        verify(gameMock).playGameSound();
        verify(gameMock).playMenuSelect();
        verify(gameMock).setState(any(MenuState.class));
    }

    @Test
    void stepSelectActionStart() throws IOException {
        when(gameOverMock.isSelectedStart()).thenReturn(true);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).stopLossSound();
        verify(gameMock).playGameSound();
        verify(gameMock).playMenuSelect();
        verify(gameMock).setState(any(GameState.class));
    }

    @Test
    void stepSelectActionNoSelection() throws IOException {
        when(gameOverMock.isSelectedExit()).thenReturn(false);
        when(gameOverMock.isSelectedStart()).thenReturn(false);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).stopLossSound();
        verify(gameMock).playGameSound();
        verify(gameMock, never()).playMenuSelect();
        verify(gameMock, never()).setState(any());
    }
}

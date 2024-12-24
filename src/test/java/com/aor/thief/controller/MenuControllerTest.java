import com.aor.thief.Game;
import com.aor.thief.controller.menu.MenuController;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.GameState;
import com.aor.thief.states.InstructionsState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class MenuControllerTest {
    private MenuController controller;
    private Menu menuMock;
    private Game gameMock;

    @BeforeEach
    void setUp() {
        menuMock = mock(Menu.class);
        gameMock = mock(Game.class);
        controller = new MenuController(menuMock);
    }

    @Test
    void stepUpAction() throws IOException {
        controller.step(gameMock, GUI.ACTION.UP, 0);

        verify(menuMock).previousEntry();
        verify(gameMock).playMenuChange();
        verify(gameMock).stopWantedSound();
        verify(gameMock).stopLossSound();
    }

    @Test
    void stepDownAction() throws IOException {
        controller.step(gameMock, GUI.ACTION.DOWN, 0);

        verify(menuMock).nextEntry();
        verify(gameMock).playMenuChange();
        verify(gameMock).stopWantedSound();
        verify(gameMock).stopLossSound();
    }

    @Test
    void stepSelectActionExit() throws IOException {
        when(menuMock.isSelectedExit()).thenReturn(true);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).setState(null);
        verify(gameMock, never()).playMenuSelect();
    }

    @Test
    void stepSelectActionInstructions() throws IOException {
        when(menuMock.isSelectedInstructions()).thenReturn(true);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).playMenuSelect();
        verify(gameMock).setState(any(InstructionsState.class));
    }

    @Test
    void stepSelectActionStart() throws IOException {
        when(menuMock.isSelectedStart()).thenReturn(true);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).playMenuSelect();
        verify(gameMock).setState(any(GameState.class));
    }

    @Test
    void stepSelectActionNoSelection() throws IOException {
        when(menuMock.isSelectedExit()).thenReturn(false);
        when(menuMock.isSelectedInstructions()).thenReturn(false);
        when(menuMock.isSelectedStart()).thenReturn(false);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock, never()).playMenuSelect();
        verify(gameMock, never()).setState(any());
    }
}

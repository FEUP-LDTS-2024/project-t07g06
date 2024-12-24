import com.aor.thief.Game;
import com.aor.thief.controller.instructions.InstructionsController;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.instructions.Instructions;
import com.aor.thief.states.GameState;
import com.aor.thief.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class InstructionsControllerTest {
    private InstructionsController controller;
    private Instructions instructionsMock;
    private Game gameMock;

    @BeforeEach
    void setUp() {
        instructionsMock = mock(Instructions.class);
        gameMock = mock(Game.class);
        controller = new InstructionsController(instructionsMock);
    }

    @Test
    void stepUpAction() throws IOException {
        controller.step(gameMock, GUI.ACTION.UP, 0);

        verify(instructionsMock).previousEntry();
        verify(gameMock).playMenuChange();
    }

    @Test
    void stepDownAction() throws IOException {
        controller.step(gameMock, GUI.ACTION.DOWN, 0);

        verify(instructionsMock).nextEntry();
        verify(gameMock).playMenuChange();
    }

    @Test
    void stepSelectActionExit() throws IOException {
        when(instructionsMock.isSelectedExit()).thenReturn(true);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).playMenuSelect();
        verify(gameMock).setState(any(MenuState.class));
    }

    @Test
    void stepSelectActionStart() throws IOException {
        when(instructionsMock.isSelectedStart()).thenReturn(true);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock).playMenuSelect();
        verify(gameMock).setState(any(GameState.class));
    }

    @Test
    void stepSelectActionNoSelection() throws IOException {
        when(instructionsMock.isSelectedExit()).thenReturn(false);
        when(instructionsMock.isSelectedStart()).thenReturn(false);

        controller.step(gameMock, GUI.ACTION.SELECT, 0);

        verify(gameMock, never()).playMenuSelect();
        verify(gameMock, never()).setState(any());
    }
}

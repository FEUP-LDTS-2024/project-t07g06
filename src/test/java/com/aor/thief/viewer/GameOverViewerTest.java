import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.gameover.GameOver;
import com.aor.thief.viewer.gameover.GameOverViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class GameOverViewerTest {
    private GameOverViewer viewer;
    private GameOver gameOverMock;
    private GUI guiMock;

    @BeforeEach
    void setUp() {
        gameOverMock = mock(GameOver.class);
        guiMock = mock(GUI.class);
        viewer = new GameOverViewer(gameOverMock);
    }

    @Test
    void drawElementsDisplaysGameOverText() {
        viewer.drawElements(guiMock);

        verify(guiMock).drawText(new Position(5, 5), "GAME OVER", "#FFFFFF");
    }

    @Test
    void drawElementsDisplaysEntriesCorrectly() {
        when(gameOverMock.getNumberEntries()).thenReturn(3);
        when(gameOverMock.getEntry(0)).thenReturn("Retry");
        when(gameOverMock.getEntry(1)).thenReturn("Main Menu");
        when(gameOverMock.getEntry(2)).thenReturn("Exit");
        when(gameOverMock.isSelected(0)).thenReturn(true);
        when(gameOverMock.isSelected(1)).thenReturn(false);
        when(gameOverMock.isSelected(2)).thenReturn(false);

        viewer.drawElements(guiMock);

        verify(guiMock).drawText(new Position(5, 7), "Retry", "#FFD700"); // Selected entry
        verify(guiMock).drawText(new Position(5, 8), "Main Menu", "#FFFFFF"); // Not selected
        verify(guiMock).drawText(new Position(5, 9), "Exit", "#FFFFFF"); // Not selected
    }

    @Test
    void drawElementsNoEntries() {

        when(gameOverMock.getNumberEntries()).thenReturn(0);

        viewer.drawElements(guiMock);


        verify(guiMock).drawText(new Position(5, 5), "GAME OVER", "#FFFFFF");


        verify(guiMock, never()).drawText(argThat(position -> position.getY() >= 7), anyString(), anyString());
    }


}

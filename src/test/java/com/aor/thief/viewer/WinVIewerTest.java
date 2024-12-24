import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.win.Win;
import com.aor.thief.viewer.win.WinViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class WinViewerTest {
    private WinViewer viewer;
    private Win winMock;
    private GUI guiMock;

    @BeforeEach
    void setUp() {
        winMock = mock(Win.class);
        guiMock = mock(GUI.class);
        viewer = new WinViewer(winMock);
    }

    @Test
    void drawVictoryMessage() {
        when(winMock.getNumberEntries()).thenReturn(0);

        viewer.drawElements(guiMock);


        verify(guiMock).drawText(new Position(5, 5), "YOU WON", "#FFFFFF");

        verify(guiMock, never()).drawText(argThat(position -> position.getY() > 5), anyString(), anyString());
    }

    @Test
    void drawDynamicEntries() {
        when(winMock.getNumberEntries()).thenReturn(3);
        when(winMock.getEntry(0)).thenReturn("Restart Game");
        when(winMock.getEntry(1)).thenReturn("Main Menu");
        when(winMock.getEntry(2)).thenReturn("Quit");
        when(winMock.isSelected(0)).thenReturn(false);
        when(winMock.isSelected(1)).thenReturn(true);
        when(winMock.isSelected(2)).thenReturn(false);

        viewer.drawElements(guiMock);


        verify(guiMock).drawText(new Position(5, 5), "YOU WON", "#FFFFFF");


        verify(guiMock).drawText(new Position(5, 7), "Restart Game", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 8), "Main Menu", "#FFD700");
        verify(guiMock).drawText(new Position(5, 9), "Quit", "#FFFFFF");
    }

    @Test
    void drawElementsNoEntries() {
        when(winMock.getNumberEntries()).thenReturn(0);

        viewer.drawElements(guiMock);


        verify(guiMock).drawText(new Position(5, 5), "YOU WON", "#FFFFFF");


        verify(guiMock, never()).drawText(argThat(position -> position.getY() > 5), anyString(), anyString());
    }
}


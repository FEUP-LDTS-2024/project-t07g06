import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.viewer.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class MenuViewerTest {
    private MenuViewer viewer;
    private Menu menuMock;
    private GUI guiMock;

    @BeforeEach
    void setUp() {
        menuMock = mock(Menu.class);
        guiMock = mock(GUI.class);
        viewer = new MenuViewer(menuMock);
    }

    @Test
    void drawTitle() {
        when(menuMock.getNumberEntries()).thenReturn(0);

        viewer.drawElements(guiMock);

        verify(guiMock).drawText(new Position(5, 5), "BANKROBBER", "#FFFFFF");
        verify(guiMock, never()).drawText(argThat(position -> position.getY() > 5), anyString(), anyString());
    }

    @Test
    void drawDynamicEntries() {
        when(menuMock.getNumberEntries()).thenReturn(3);
        when(menuMock.getEntry(0)).thenReturn("Start Game");
        when(menuMock.getEntry(1)).thenReturn("Options");
        when(menuMock.getEntry(2)).thenReturn("Exit");
        when(menuMock.isSelected(0)).thenReturn(false);
        when(menuMock.isSelected(1)).thenReturn(true);
        when(menuMock.isSelected(2)).thenReturn(false);

        viewer.drawElements(guiMock);


        verify(guiMock).drawText(new Position(5, 5), "BANKROBBER", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 7), "Start Game", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 8), "Options", "#FFD700");
        verify(guiMock).drawText(new Position(5, 9), "Exit", "#FFFFFF");
    }

    @Test
    void drawElementsNoEntries() {
        when(menuMock.getNumberEntries()).thenReturn(0);

        viewer.drawElements(guiMock);


        verify(guiMock).drawText(new Position(5, 5), "BANKROBBER", "#FFFFFF");


        verify(guiMock, never()).drawText(argThat(position -> position.getY() > 5), anyString(), anyString());
    }
}


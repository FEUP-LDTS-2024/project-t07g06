import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.instructions.Instructions;
import com.aor.thief.viewer.instructions.InstructionsViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class InstructionsViewerTest {
    private InstructionsViewer viewer;
    private Instructions instructionsMock;
    private GUI guiMock;

    @BeforeEach
    void setUp() {
        instructionsMock = mock(Instructions.class);
        guiMock = mock(GUI.class);
        viewer = new InstructionsViewer(instructionsMock);
    }

    @Test
    void drawFixedElements() {
        when(instructionsMock.getNumberEntries()).thenReturn(0);

        viewer.drawElements(guiMock);

        verify(guiMock).drawText(new Position(5, 5), "INSTRUCTIONS", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 7), "to move use: wasd or arrows", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 8), "to shoot use: space", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 9), "to leave through the door use: E", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 10), "to quit use: Q", "#FFFFFF");

        verify(guiMock, never()).drawText(argThat(position -> position.getY() >= 12), anyString(), anyString());
    }

    @Test
    void drawDynamicEntries() {

        when(instructionsMock.getNumberEntries()).thenReturn(2);
        when(instructionsMock.getEntry(0)).thenReturn("First Entry");
        when(instructionsMock.getEntry(1)).thenReturn("Second Entry");
        when(instructionsMock.isSelected(0)).thenReturn(false);
        when(instructionsMock.isSelected(1)).thenReturn(true);

        viewer.drawElements(guiMock);


        verify(guiMock).drawText(new Position(5, 5), "INSTRUCTIONS", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 7), "to move use: wasd or arrows", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 8), "to shoot use: space", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 9), "to leave through the door use: E", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 10), "to quit use: Q", "#FFFFFF");

        
        verify(guiMock).drawText(new Position(5, 12), "First Entry", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 13), "Second Entry", "#FFD700");
    }

    @Test
    void drawSingleEntry() {

        when(instructionsMock.getNumberEntries()).thenReturn(1);
        when(instructionsMock.getEntry(0)).thenReturn("First Entry");
        when(instructionsMock.isSelected(0)).thenReturn(true);

        viewer.drawElements(guiMock);


        verify(guiMock).drawText(new Position(5, 5), "INSTRUCTIONS", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 7), "to move use: wasd or arrows", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 8), "to shoot use: space", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 9), "to leave through the door use: E", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 10), "to quit use: Q", "#FFFFFF");


        verify(guiMock).drawText(new Position(5, 12), "First Entry", "#FFD700");
    }

    @Test
    void drawNoEntries() {

        when(instructionsMock.getNumberEntries()).thenReturn(0);

        viewer.drawElements(guiMock);


        verify(guiMock).drawText(new Position(5, 5), "INSTRUCTIONS", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 7), "to move use: wasd or arrows", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 8), "to shoot use: space", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 9), "to leave through the door use: E", "#FFFFFF");
        verify(guiMock).drawText(new Position(5, 10), "to quit use: Q", "#FFFFFF");


        verify(guiMock, never()).drawText(argThat(position -> position.getY() >= 12), anyString(), anyString());
    }
}


import com.aor.thief.gui.LanternaGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI lanternaGUI= new LanternaGUI(15,15);
        lanternaGUI.draw();
    }
}

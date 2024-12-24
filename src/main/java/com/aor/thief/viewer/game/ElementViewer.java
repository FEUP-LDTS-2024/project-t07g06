package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Element;

import java.io.IOException;

public interface ElementViewer <T extends Element>{
    void draw(T element, GUI gui) throws IOException, InterruptedException;
}

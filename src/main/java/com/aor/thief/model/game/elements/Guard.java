package com.aor.thief.model.game.elements;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Guard extends ConeFov {
    private boolean found;

    public Guard(int x, int y, int direction, boolean found) {
        super(x, y,direction);
        this.found = found;
    }



    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }




}

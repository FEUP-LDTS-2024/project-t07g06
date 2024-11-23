package com.aor.thief.model.game.arena;

public class Arena {
    private final int width;
    private final int height;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

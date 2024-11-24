package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Guard;

import java.io.IOException;

public class GuardController extends GameController{
    private long lastMovement;

    public GuardController(Arena arena){
        super(arena);
        this.lastMovement=0;
    }

    @Override
    public void step(Game game, GUI.ACTION action,long time) throws IOException {
        int index =0;
        if (time - lastMovement > 500) {
            for (Guard guard : getModel().getGuards()) {
                int cur_dir= guard.getDirection();
                System.out.println("dir");
                System.out.println(cur_dir);
                System.out.println("Guard: ");
                System.out.println(guard.getPosition().getX());
                System.out.println(guard.getPosition().getY());
                System.out.println(getModel().canElementmove(guard.getPosition().movingpattern(cur_dir)));
                while (!getModel().canElementmove(guard.getPosition().movingpattern(cur_dir))) {
                    getModel().getGuards().get(index).setDirection(cur_dir++);
                    System.out.println("New Dir: ");
                    System.out.println(cur_dir);
                    if (cur_dir>3) guard.setDirection(cur_dir++);
                }
                moveGuard(index, guard.getPosition().movingpattern(cur_dir));
                index++;
            }
            this.lastMovement = time;
            index=0;
        }
    }
    private void moveGuard(int index, Position position) {
        if (getModel().canElementmove(position)){
            getModel().getGuards().get(index).setPosition(position);
        }
    }
}

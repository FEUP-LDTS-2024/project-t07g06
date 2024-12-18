package com.aor.thief.controller.game;

import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Thief;
import com.aor.thief.model.gameover.GameOver;
import com.aor.thief.model.menu.Menu;
import com.aor.thief.states.GameOverState;
import com.aor.thief.states.MenuState;

import java.io.IOException;
import java.util.*;

public class CopController extends GameController {
    private long lastMovement;
    private Thread moveAnimation;

    public CopController(Arena arena) {
        super(arena);
        this.lastMovement = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        int index = 0;
        if (!getModel().getCops().isEmpty()) {
            if (isCaught()) {
                for (Guard guard : getModel().getGuards()) {
                    guard.setFound(true);
                }
                for (Cop cop : getModel().getCops()) {
                    cop.setFound(true);
                }
            }
            if (time - lastMovement > 500) {
                if (!getModel().getCops().isEmpty()) {
                    if (isCaught()) {
                        game.stopGameSound();
                        game.playWantedSound();
                        for (Guard guard : getModel().getGuards()) {
                            guard.setFound(true);
                        }
                        for (Cop cop : getModel().getCops()) {
                            cop.setFound(true);
                        }
                    }
                    if (!getModel().getCops().getFirst().isFound()) {
                        for (Cop cop : getModel().getCops()) {
                            int cur_dir = cop.getDirection();
                            int counting_dir = cur_dir;
                            if (!cop.getHitbox().isEmpty()) {
                                for (Position position : cop.getHitbox()) {
                                    if (!getModel().isntWall(position.getDown())) {
                                        cop.setDown(false);
                                    }
                                    if (!getModel().isntWall(position.getUp())) {
                                        cop.setDown(true);
                                    }
                                    if (!getModel().isntWall(movement(position, cur_dir, index))) {
                                        cur_dir++;
                                        counting_dir++;
                                        if (cur_dir > 3) cur_dir = 0;
                                        if (counting_dir > cur_dir + 9) break;
                                    }
                                }
                            }
                            cop.setDirection(cur_dir);
                            moveCop(index, movement(cop.getPosition(), cur_dir, index));
                            if (cur_dir % 2 == 0) {
                                cop.setDirection(cur_dir + 1);
                            }
                            index++;
                        }
                        this.lastMovement = time;
                        if (isCaught()) {
                            for (Guard guard : getModel().getGuards()) {
                                guard.setFound(true);
                            }
                            for (Cop cop : getModel().getCops()) {
                                cop.setFound(true);
                            }
                        }
                    } else {
                        for (Cop cop : getModel().getCops()) {
                            Map<Position, Boolean> path = new HashMap<>();
                            pursuit(cop, getModel().getThief(), index);
                            if (cop.getPosition().equals(getModel().getThief().getPosition())) {
                                game.stopGameSound();
                                game.stopWantedSound();
                                game.playLossSound();
                                game.setState(new GameOverState(new GameOver()));
                            }
                            index++;
                            this.lastMovement = time;
                        }

                    }
                }
            }
        }
    }

    public boolean isCaught() {
        for (Cop cop : getModel().getCops()) {
            for (Position caught : getModel().fov(cop)) {
                if (getModel().getThief().getHitbox().contains(caught)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void moveCop(int index, Position position) {
        boolean flag = true;
        for(Position p: position.updatehitbox_()) {
            if (!getModel().isntWall(p)) {
                flag = false;
            }
        }
        if (flag) {
            getModel().getCops().get(index).setPosition(position);
            getModel().getCops().get(index).setHitbox(position.updatehitbox_());
        }
    }



    List<Position> getNeighbors(Position p) {
        List<Position> neighbors = new ArrayList<>();
        int x = p.getX();
        int y = p.getY();
        neighbors.add(new Position(x - 1, y));
        neighbors.add(new Position(x + 1, y));
        neighbors.add(new Position(x, y - 1));
        neighbors.add(new Position(x, y + 1));
        return neighbors;
    }




    public void pursuit(Cop cop, Thief thief, int cop_index) {
        int min_dist = Integer.MAX_VALUE;
        Position nierest = null;
        for(Position p: getNeighbors(cop.getPosition())) {
            if(p.distance(thief.getPosition())<min_dist) {
                min_dist = p.distance(thief.getPosition());
                nierest = p;
            }
        }
        moveCop(cop_index, new Position(nierest.getX(), nierest.getY()));
    }
    public Position movement(Position position, int direction, int index){
        switch (direction){
            case 1:
                return position.getLeft();
            case 3:
                return position.getRight();
            default:
                if(getModel().getCops().get(index).isDown()) return position.getDown();
                else return position.getUp();
        }
    }
    private synchronized void startMoveAnimation(Cop cop) {
        String direction = "";
        switch (cop.getDirection()) {
            case 0:
                direction = "down";
                break;
            case 1:
                direction = "left";
                break;
            case 2:
                direction = "up";
                break;
            case 3:
                direction = "right";
                break;
        }
        if (moveAnimation == null || !moveAnimation.isAlive()) {
            String finalDirection = direction;
            moveAnimation = new Thread(() -> {
                try {
                    if (cop.getFeet() == 0) {
                        cop.setDraw("rightleg_" + finalDirection + ".png");
                    } else {
                        cop.setDraw("leftleg_" + finalDirection + ".png");
                    }
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            moveAnimation.start();
        }
    }
}
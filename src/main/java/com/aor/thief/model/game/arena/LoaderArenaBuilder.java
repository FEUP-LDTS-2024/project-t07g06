package com.aor.thief.model.game.arena;

import com.aor.thief.model.Position;
import com.aor.thief.model.game.elements.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoaderArenaBuilder extends ArenaBuilder {
    private final int level;
    private final List<String> lines;

    public LoaderArenaBuilder(int level) throws IOException {
        this.level = level;

        URL resource = LoaderArenaBuilder.class.getResource("/levels/level" + level + ".lvl");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; ) {
            lines.add(line);
        }
        return lines;
    }

    @Override
    protected int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    @Override
    protected int getHeight() {
        return lines.size();
    }

    @Override
    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '#'){
                    List <Position> hitbox = new ArrayList<>();
                    hitbox.add(new Position(x,y));
                    hitbox.add(new Position(x+1,y));
                    hitbox.add(new Position(x+1,y+1));
                    hitbox.add(new Position(x,y+1));
                    hitbox.add(new Position(x-1,y+1));
                    hitbox.add(new Position(x-1,y));
                    hitbox.add(new Position(x-1,y-1));
                    hitbox.add(new Position(x,y-1));
                    hitbox.add(new Position(x+1,y-1));
                    walls.add(new Wall(x, y,hitbox));
                }
        }

        return walls;
    }

    @Override
    protected List<DeadBody> createDeads() {
        return new ArrayList<>();
    }

    @Override
    protected List<Guard> createGuard() {
        List<Guard> guards = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'G') {
                    List <Position> hitbox = new ArrayList<>();
                    hitbox.add(new Position(x,y));
                    hitbox.add(new Position(x+1,y));
                    hitbox.add(new Position(x+1,y+1));
                    hitbox.add(new Position(x,y+1));
                    hitbox.add(new Position(x-1,y+1));
                    hitbox.add(new Position(x-1,y));
                    hitbox.add(new Position(x-1,y-1));
                    hitbox.add(new Position(x,y-1));
                    hitbox.add(new Position(x+1,y-1));
                    guards.add(new Guard(x, y, 0, false,hitbox));
                }
        }

        return guards;
    }

    @Override
    protected Thief createThief() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'T'){
                    List <Position> hitbox = new ArrayList<>();
                    hitbox.add(new Position(x,y));
                    hitbox.add(new Position(x+1,y));
                    hitbox.add(new Position(x+1,y+1));
                    hitbox.add(new Position(x,y+1));
                    hitbox.add(new Position(x-1,y+1));
                    hitbox.add(new Position(x-1,y));
                    hitbox.add(new Position(x-1,y-1));
                    hitbox.add(new Position(x,y-1));
                    hitbox.add(new Position(x+1,y-1));
                    return new Thief(x, y,0,0,hitbox);
                }
        }
        return null;
    }
    @Override
    protected List<Prize> createPrize() {
        List<Prize> prize = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'P') {
                    List <Position> hitbox = new ArrayList<>();
                    hitbox.add(new Position(x,y));
                    hitbox.add(new Position(x+1,y));
                    hitbox.add(new Position(x+1,y+1));
                    hitbox.add(new Position(x,y+1));
                    hitbox.add(new Position(x-1,y+1));
                    hitbox.add(new Position(x-1,y));
                    hitbox.add(new Position(x-1,y-1));
                    hitbox.add(new Position(x,y-1));
                    hitbox.add(new Position(x+1,y-1));
                    prize.add( new Prize(x, y,hitbox));
                }
        }
        return prize;
    }

    @Override
    protected Door createDoor() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'T'){
                    List <Position> hitbox = new ArrayList<>();
                    hitbox.add(new Position(x,y));
                    hitbox.add(new Position(x+1,y));
                    hitbox.add(new Position(x+1,y+1));
                    hitbox.add(new Position(x,y+1));
                    hitbox.add(new Position(x-1,y+1));
                    hitbox.add(new Position(x-1,y));
                    hitbox.add(new Position(x-1,y-1));
                    hitbox.add(new Position(x,y-1));
                    hitbox.add(new Position(x+1,y-1));
                    return new Door(x, y,hitbox);
                }
        }
        return null;
    }

    @Override
    protected List<Camera> createCamera(){
        List<Camera> cam = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'C') {
                    List <Position> hitbox = new ArrayList<>();
                    hitbox.add(new Position(x,y));
                    hitbox.add(new Position(x+1,y));
                    hitbox.add(new Position(x+1,y+1));
                    hitbox.add(new Position(x,y+1));
                    hitbox.add(new Position(x-1,y+1));
                    hitbox.add(new Position(x-1,y));
                    hitbox.add(new Position(x-1,y-1));
                    hitbox.add(new Position(x,y-1));
                    hitbox.add(new Position(x+1,y-1));
                    cam.add(new Camera(x, y,0, false ,hitbox, new ArrayList<>()));
                }
        }

        return cam;

    }
    @Override
    protected List<CameraStill> createStillCameras() {
        List<CameraStill> cam = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'S') {
                    List<Position> hitbox = new ArrayList<>();
                    hitbox.add(new Position(x, y));
                    hitbox.add(new Position(x + 1, y));
                    hitbox.add(new Position(x + 1, y + 1));
                    hitbox.add(new Position(x, y + 1));
                    hitbox.add(new Position(x - 1, y + 1));
                    hitbox.add(new Position(x - 1, y));
                    hitbox.add(new Position(x - 1, y - 1));
                    hitbox.add(new Position(x, y - 1));
                    hitbox.add(new Position(x + 1, y - 1));
                    cam.add(new Camera(x, y, 0, false, hitbox, new ArrayList<>()));
                }
        }

        return cam;
    }
    @Override
    protected List<Cop> createCop() {
        List<Cop> cop = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'O') {
                    List<Position> hitbox = new ArrayList<>();
                    hitbox.add(new Position(x, y));
                    hitbox.add(new Position(x + 1, y));
                    hitbox.add(new Position(x + 1, y + 1));
                    hitbox.add(new Position(x, y + 1));
                    hitbox.add(new Position(x - 1, y + 1));
                    hitbox.add(new Position(x - 1, y));
                    hitbox.add(new Position(x - 1, y - 1));
                    hitbox.add(new Position(x, y - 1));
                    hitbox.add(new Position(x + 1, y - 1));
                    cop.add(new Cop(x, y, 1, false, hitbox));
                }
        }
        return cop;
    }


}
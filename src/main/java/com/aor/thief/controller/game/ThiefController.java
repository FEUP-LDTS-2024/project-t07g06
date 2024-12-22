package com.aor.thief.controller.game;
import com.aor.thief.Game;
import com.aor.thief.gui.GUI;
import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.Arena;
import com.aor.thief.model.game.elements.Cop;
import com.aor.thief.model.game.elements.DeadBody;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.win.Win;
import com.aor.thief.sound.InstanceSound;
import com.aor.thief.sound.SoundFactory;
import com.aor.thief.states.WinState;
import com.aor.thief.model.game.elements.Thief;


import java.io.IOException;

public class ThiefController extends GameController {
    Thread moveAnimation;
    Thread attackAnimation;
    public ThiefController(Arena arena) {
        super(arena);
    }

    public void moveThiefLeft() {
        getModel().getThief().setDirection(1);
        moveThief(getModel().getThief().getPosition().getLeft());
    }

    public void moveThiefUp() {
        getModel().getThief().setDirection(2);
        moveThief(getModel().getThief().getPosition().getUp());
    }

    public void moveThiefRight() {
        getModel().getThief().setDirection(3);
        moveThief(getModel().getThief().getPosition().getRight());
    }

    public void moveThiefDown() {
        getModel().getThief().setDirection(0);
        moveThief(getModel().getThief().getPosition().getDown());
    }

    public boolean canThiefEscape() {
        for(Position p: getModel().getThief().getHitbox()) {
            if(!getModel().getDoor().getHitbox().isEmpty()&&getModel().getDoor().getHitbox().contains(p)&&getModel().getPrize().isEmpty()){
               return true;
            }
        }
        return false;
    }

    private void moveThief(Position position) {
        boolean flag = true;
        for(Position p: position.updatehitbox_()) {
            if (!getModel().isntWall(p)) {
                flag = false;
            }
        }
        if (flag) {
            getModel().getThief().setPosition(position);
            getModel().getThief().setHitbox(position.updatehitbox_());
        }
    }

    public boolean canKill()  {
        for (Guard guard : getModel().getGuards()) {
            if (getModel().fov(getModel().getThief()).stream().anyMatch(guard.getHitbox()::contains)) {
                killGuard(guard);
                return true;
            }
        }
        for (Cop cop : getModel().getCops()) {
            if (getModel().getThief().getFov().stream().anyMatch(cop.getHitbox()::contains)) {
                killCop(cop);
                return true;
            }
        }
        return false;
    }
    private synchronized void startMoveAnimation(Thief thief)  {
        String direction = switch (thief.getDirection()) {
            case 0 -> "down1";
            case 1 -> "left1";
            case 2 -> "up1";
            case 3 -> "right1";
            default -> "";
        };
        if (moveAnimation == null || !moveAnimation.isAlive()) {
            moveAnimation = new Thread(() -> {
                try {
                    if(getModel().getThief().getFeet()==0) {
                        thief.setDraw("rightleg_" + direction + ".png");
                        Thread.sleep(150);
                    }else {
                        thief.setDraw("leftleg_" + direction + ".png");
                        Thread.sleep(150);
                    }
                    thief.toggleFeet();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            moveAnimation.start();
        }
    }

    private synchronized void startAttackAnimation(Thief thief)  {
        String direction = switch (thief.getDirection()) {
            case 0 -> "down1";
            case 1 -> "left1";
            case 2 -> "up1";
            case 3 -> "right1";
            default -> "";
        };
        if (attackAnimation == null || !attackAnimation.isAlive()) {
            attackAnimation = new Thread(() -> {
                try {
                    String shoot = "shoot_" + direction + ".png";

                    thief.setDraw(shoot);
                    Thread.sleep(100);
                    String still = "still_" + direction + ".png";
                    thief.setDraw(still);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    attackAnimation = null;
                }
            });
            attackAnimation.start();
        }
    }

    public  void playFootStepSound() { //como footstep é chamado várias vezes num curto espaço de tempo,é melhor criar uma nova instância de footstep a cada utilização
        InstanceSound footStepSound = new SoundFactory().createFootStepSound();
        footStepSound.start();
    }

    public  void playGunShot() {
        InstanceSound gunShot = new SoundFactory().createGunShotSound();
        gunShot.start();
    }

    public void PickedUpPrize(){
        if (!getModel().getPrize().isEmpty()) {
            if(getModel().getThief().getHitbox().stream().anyMatch(getModel().getPrize().getFirst().getHitbox()::contains)) {
                getModel().getThief().prize_collected();
                playItemPickup();
                getModel().getPrize().removeFirst();
            }
        }
    }

    public void killGuard(Guard guard){
        getModel().addDead(new DeadBody(guard.getPosition().getX(), guard.getPosition().getY(),guard.getHitbox(),"G"));
        getModel().getGuards().remove(guard);
    }

    public void killCop(Cop cop){
        getModel().addDead(new DeadBody(cop.getPosition().getX(), cop.getPosition().getY(),cop.getHitbox(),"C"));
        getModel().getCops().remove(cop);
    }

    public void playItemPickup(){
        InstanceSound item=new SoundFactory().createItemPickupSound();
        item.start();
    }

    public  void playGuardDeathSound() {
        InstanceSound guardDeathSound = new SoundFactory().createGuardDeathSound();
        guardDeathSound.start();
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, InterruptedException {
        if (action == GUI.ACTION.UP) {
            playFootStepSound();
            startMoveAnimation(getModel().getThief());
            if(getModel().getThief().getDirection()!=2) getModel().getThief().move("up");
            moveThiefUp();
            PickedUpPrize();
        }

        if (action == GUI.ACTION.DOWN) {
            playFootStepSound();
            startMoveAnimation(getModel().getThief());
            if(getModel().getThief().getDirection()!=0) getModel().getThief().move("down");
            moveThiefDown();
            PickedUpPrize();
        }

        if (action == GUI.ACTION.LEFT) {
            playFootStepSound();
            startMoveAnimation(getModel().getThief());
            if(getModel().getThief().getDirection()!=1) getModel().getThief().move("left");
            moveThiefLeft();
            PickedUpPrize();
        }
        if (action == GUI.ACTION.RIGHT) {


            playFootStepSound();

            startMoveAnimation(getModel().getThief());
            if(getModel().getThief().getDirection()!=3) getModel().getThief().move("right");
            moveThiefRight();
            PickedUpPrize();
        }
        if (action == GUI.ACTION.ESCAPE) {
            if (canThiefEscape()) {
                game.playWin();
                game.setState(new WinState(new Win()));
            }
        }
        if (action == GUI.ACTION.KILL) {

            startAttackAnimation(getModel().getThief());
            playGunShot();
            if(canKill()) playGuardDeathSound();





        }
    }
}
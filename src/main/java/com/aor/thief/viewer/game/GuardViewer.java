package com.aor.thief.viewer.game;

import com.aor.thief.gui.GUI;
import com.aor.thief.model.game.elements.Guard;
import com.aor.thief.model.game.elements.Wall;

public class GuardViewer implements ElementViewer<Guard> {
    @Override
    public void draw(Guard guard, GUI gui) {
        gui.drawGuard(guard.getPosition());
    }
}
        //switch (guard.getDirection()) {
          //  case (0):
            //    gui.drawImage(guard.getPosition(), "down");
              //  break;
            //case (1):
             //   gui.drawImage(guard.getPosition(), "left");
              //  break;
           // case (2):
               // gui.drawImage(guard.getPosition(), "up");
                //break;
           // case (3):
             //   gui.drawImage(guard.getPosition(), "right");
               // break;
        //}
    //}
//}

package com.replilab.worm;

import com.replilab.worm.ai.CoordinateObserver;
import java.util.Set;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Food implements Actor, Hittable {
    public static int CurrentX, CurrentY;
    private Image imageCell;
    private final GraphicsContext gc;
    //private CoordinateObserver aiAware;

    public Food(GraphicsContext gc, Set onScreenObjectSet) {
        this.gc=gc;
       // this.aiAware=aiAware;
        reposition();
        this.imageCell = new Image(getClass().getResourceAsStream("food.bmp"));
        onScreenObjectSet.add(this);

    }

    private void reposition() {
        CurrentX = (int) (Math.random() * 26) * 30;
        CurrentY = (int) (Math.random() * 26) * 30;
       // aiAware.update("food", CurrentX,CurrentY);
    }
    public void work() {
        gc.drawImage(imageCell, CurrentX + 1, CurrentY + 1);
    }

    public boolean doesIhitYou(int x, int y) {
        if (CurrentX == x && CurrentY == y) {
           // aiAware.delete("food",CurrentX,CurrentY);
            reposition();
            return true;
        }
        return false;
    }
}

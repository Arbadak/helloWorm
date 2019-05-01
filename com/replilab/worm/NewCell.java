package com.replilab.worm;

import com.replilab.worm.ai.CoordinateObserver;
import java.util.Set;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NewCell {

    private NewCell childCell;
    private int CurrentX, CurrentY;
    private int OldX, OldY;
    private Image imageCell;
    private Set onScreenObjectSet;

    public NewCell(GraphicsContext gc, int currentX, int currentY, Image image, Set onScreenObjectSet) {
        this.CurrentX = currentX;
        this.CurrentY = currentY;
        this.imageCell = image;
        this.onScreenObjectSet = onScreenObjectSet;
        onScreenObjectSet.add(this);
    }

    public void work(GraphicsContext gc, int x, int y, String event) {
        reposition(x, y);
        drawSelf(gc);
        if (childCell!=null) {
            childCell.work(gc, OldX, OldY, event);}
        else if (event == "grow" && childCell == null) {
            childCell = new NewCell(gc, x, y, imageCell, onScreenObjectSet);
        }
    }

    public void reposition(int x, int y) {
        OldX = this.CurrentX;
        CurrentX = x;
        OldY = this.CurrentY;
        CurrentY = y;
    }
    public void drawSelf(GraphicsContext gc){
        gc.drawImage(imageCell, CurrentX+1, CurrentY+1);
     }


}






package com.replilab.worm;

import com.replilab.worm.ai.CoordinateObserver;
import java.util.Set;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Cell {

    private Cell childCell;
    private int CurrentX, CurrentY;
    private int OldX, OldY;
    private Image imageCell;
    private Set onScreenObjectSet;
    private CoordinateObserver observer;
    private GraphicsContext gc;

    public Cell(GraphicsContext gc, int currentX, int currentY, Image image, Set onScreenObjectSet, CoordinateObserver observer) {
        this.CurrentX = currentX;
        this.CurrentY = currentY;
        this.imageCell = image;
        this.onScreenObjectSet = onScreenObjectSet;
        onScreenObjectSet.add(this);
        this.observer=observer;
        this.gc=gc;
    }

    public void work(int x, int y, String event) {
        reposition(x, y);
        drawSelf(gc);
        if (childCell!=null) {
            childCell.work(OldX, OldY, event);}
        else if (event == "grow" && childCell == null) {
            childCell = new Cell(gc, x, y, imageCell, onScreenObjectSet, observer);
        }
        observer.update(this, CurrentX, CurrentY);
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






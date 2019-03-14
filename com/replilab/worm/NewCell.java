package com.replilab.worm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NewCell {

    private NewCell childCell;
    private int CurrentX, CurrentY;
    private int OldX, OldY;
    private Image imageCell;

    public NewCell(GraphicsContext gc, int currentX, int currentY) {
        this.CurrentX = currentX;
        this.CurrentY = currentY;
        this.imageCell = new Image(getClass().getResourceAsStream("cell.bmp"));
    }

    public void work(GraphicsContext gc, int x, int y, String event) {
        reposition(x, y);
        drawSelf(gc);
        if (childCell!=null) {
            childCell.work(gc, OldX, OldY, event);}
        else if (event == "grow" && childCell == null) {
            childCell = new NewCell(gc, x, y);
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






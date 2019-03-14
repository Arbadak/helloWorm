package com.replilab.worm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NewCell {

    private NewCell childCell;
    private int CurrentX, CurrentY;
    private int OldX, OldY;
    private Image imageCell;
    private int cellN;

    public NewCell(GraphicsContext gc, int currentX, int currentY, int number) {
        this.childCell = childCell;
        this.CurrentX = currentX;
        this.CurrentY = currentY;
        this.imageCell = new Image(getClass().getResourceAsStream("cell.bmp"));
        this.cellN=number;
    }

    public void work(GraphicsContext gc, int x, int y, String event) {
        reposition(x, y);
        drawSelf(gc);
        if (childCell!=null) {
            childCell.work(gc, x, y, event);}
        else if (event == "grow" && childCell == null) {
            childCell = new NewCell(gc, x, y, ++cellN);}

    }

    public void reposition(int x, int y) {
        OldX = CurrentX;
        CurrentX = x;
        OldY = CurrentY;
        CurrentY = y;
    }
    public void drawSelf(GraphicsContext gc){
        gc.drawImage(imageCell, CurrentX+1, CurrentY+1);


    }


}






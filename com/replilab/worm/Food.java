package com.replilab.worm;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Food {
    private int CurrentX, CurrentY;
    private Image imageCell;

    public Food() {
        reposition();
        this.imageCell = new Image(getClass().getResourceAsStream("food.bmp"));
    }
    private void reposition(){
        CurrentX = (int) (Math.random() * 26)*30;
        CurrentY = (int) (Math.random() * 26)*30;
    }

    public void work(GraphicsContext gc) {
        drawSelf(gc);
    }

    public boolean doesIbiteYou(int x, int y){

        if (CurrentX==x && CurrentY==y){
            reposition();
            return true;
        }
        return false;
    }
    public void drawSelf(GraphicsContext gc){
        gc.drawImage(imageCell, CurrentX+1, CurrentY+1);}
}

package com.replilab.worm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Boundry implements Actor, Hittable {
    private int startX=0;
    private int endX=30*26;
    private int startY=0;
    private  int endY=30*26;
    private Image image;
    private final GraphicsContext gc;
    public Boundry(GraphicsContext gc) {
        this.gc=gc;
        image = new Image(getClass().getResourceAsStream("bound.bmp"));
    }


    public void work(){
        for(int i=startX; i<endX;i=i+30){
            gc.drawImage(image,i,startY);
            gc.drawImage(image,i,endY);
        }
        for(int i=startY; i<endY;i=i+30){
            gc.drawImage(image,startY,i);
            gc.drawImage(image,endY,i);
        }
    }


    @Override
    public boolean doesIhitYou(int x, int y) {

        return false;
    }
}

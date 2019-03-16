package com.replilab.worm;

import javafx.scene.canvas.GraphicsContext;

public class StatBar {
    private int cellCount=0;

    public void work(GraphicsContext gc){
        gc.fillText("Current length: "+cellCount, 100,100);
    }
    public void increment(){cellCount++;}

}

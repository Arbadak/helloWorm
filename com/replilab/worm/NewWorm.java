package com.replilab.worm;

import javafx.scene.canvas.GraphicsContext;

public class NewWorm extends Worm {
    StatBar statBar;
    NewHead SnAkE;
    @Override
    public void begin() {
        statBar =  new StatBar();
        SnAkE=new NewHead(statBar);



    }

    @Override
    public void render(GraphicsContext gc) {

        gc.clearRect(0,0,800,800);

        SnAkE.work(gc);
        statBar.work(gc);


    }

    @Override
    public void addCell() {

    }

    @Override
    public void changeDirection(String direction) {

        SnAkE.changeDirection(direction);

    }

    @Override
    public void makeStep() {

    }

    @Override
    public void foodRepositioning() {

    }
}

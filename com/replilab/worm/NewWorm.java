package com.replilab.worm;

import javafx.scene.canvas.GraphicsContext;

public class NewWorm extends Worm {

    NewHead SnAkE;

    @Override
    public void begin() {

        SnAkE=new NewHead();

    }

    @Override
    public void render(GraphicsContext gc) {

        gc.clearRect(0,0,800,800);

        SnAkE.work(gc);


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

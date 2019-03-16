package com.replilab.worm;

import javafx.scene.canvas.GraphicsContext;

public class NewWorm extends Worm {
    StatBar statBar;
    NewHead SnAkE;
    NewHead AutoSnake;
    NewHead AutoSnake2;
    NewHead AutoSnake3;
    NewHead AutoSnake4;
    Food food;
    Boundry boundry;
    @Override
    public void begin() {
        statBar =  new StatBar();
        food= new Food();
        SnAkE=new NewHead(food, statBar,false);
        AutoSnake=new NewHead(food, statBar,true);
        AutoSnake2=new NewHead(food, statBar,true);
        boundry= new Boundry();



    }

    @Override
    public void render(GraphicsContext gc) {

        gc.clearRect(30,30,770,770);
        boundry.work(gc);
        SnAkE.work(gc);
        statBar.work(gc);
        AutoSnake.work(gc);
        AutoSnake2.work(gc);
        food.work(gc);

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

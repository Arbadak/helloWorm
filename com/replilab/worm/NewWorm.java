package com.replilab.worm;

import com.replilab.worm.ai.CoordinateObserver;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.canvas.GraphicsContext;

public class NewWorm extends Worm {
    NewHead SnAkE;
    List <Actor> actorList=new ArrayList<>();
    GraphicsContext gc;
    //CoordinateObserver aiAwareMap = new CoordinateObserver();
    Set<Actor> onSceneObjectSet=new HashSet<>();

    public NewWorm(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void begin() {
        Food food= new Food(gc, onSceneObjectSet);
        SnAkE=new NewHead(food, gc, onSceneObjectSet);
        actorList.add(food);
        actorList.add(SnAkE);
        actorList.add( new Boundry(gc));
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.clearRect(30,30,770,770);
        actorList.forEach(Actor::work);
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

package com.replilab.worm;

import com.replilab.worm.ai.CoordinateObserver;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class Worm {
    Head SnAkE;
    List <Actor> actorList=new ArrayList<>();
    GraphicsContext gc;
    //CoordinateObserver aiAwareMap = new CoordinateObserver();
    Set<Actor> onSceneObjectSet=new HashSet<>();
    CoordinateObserver observer=new CoordinateObserver();

    public Worm(GraphicsContext gc) {
        this.gc = gc;
    }


    public void begin() {
        Food food= new Food(gc, onSceneObjectSet);
        SnAkE=new Head(food, gc, onSceneObjectSet, observer);
        //SnAkE=new AutoHead(food, gc, onSceneObjectSet);
        actorList.add(food);
        actorList.add(SnAkE);
        actorList.add( new Boundry(gc));
    }


    public void render(GraphicsContext gc) {
        gc.clearRect(30,30,770,770);
        actorList.forEach(Actor::work);
    }

    public void changeDirection(KeyCode direction) {

        SnAkE.changeDirection(direction);

    }

}

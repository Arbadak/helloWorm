package com.replilab.worm;

import com.replilab.worm.ai.MapBuilder;
import com.replilab.worm.ai.XYpair;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AutoHead implements Actor {
    private Map<String, Image> imgRes = new ConcurrentHashMap<>();
    private int CurrentY, CurrentX;
    private int x, y; //Координаты до пересчета нужны для отправки цепочке звеньев
    private NewCell childCell;
    private Food food;
    private String event;
    private final GraphicsContext gc;
    Set onScreenObjectSet;
    MapBuilder mapBuilder;
    LinkedList<XYpair>path;



    public AutoHead (Food food, GraphicsContext gc, Set onScreenObjectSet) {

        CurrentX = (int) (Math.random() * 20) * 30 + 180;
        CurrentY = (int) (Math.random() * 26) * 30 + 180;
        this.gc=gc;
        graphicResourseLoader();
        this.food = food;
        this.onScreenObjectSet=onScreenObjectSet;
        onScreenObjectSet.add(this);
        mapBuilder=new MapBuilder();
        path=new LinkedList<>();
    }


    public void work() {

        reposition();
        drawSelf(gc);
        checkCollide();

        if (event == "grow" && childCell == null) {
            childCell = new NewCell(gc, x, y, imgRes.get("CELL"),onScreenObjectSet );
        }else if (childCell != null) {
            childCell.work(gc, x, y, event);}
    }
    private void checkCollide() {
        if (food.doesIhitYou(CurrentX, CurrentY) == true) {
            event = "grow";
        } else event = "tick";
    }
    private void drawSelf(GraphicsContext gc) {
        gc.drawImage(imgRes.get("UP"), CurrentX + 1, CurrentY + 1);
    }


    private void graphicResourseLoader() {
        imgRes.put("UP", new Image(getClass().getResourceAsStream("headup.bmp")));
        imgRes.put("DOWN", new Image(getClass().getResourceAsStream("headdown.bmp")));
        imgRes.put("LEFT", new Image(getClass().getResourceAsStream("headleft.bmp")));
        imgRes.put("RIGHT", new Image(getClass().getResourceAsStream("headright.bmp")));
        imgRes.put("CELL", new Image(getClass().getResourceAsStream("cell.bmp")));
    }


    private void reposition(){
       if(path.isEmpty()) {mapBuilder.calculatePath(new XYpair(CurrentX, CurrentY));}
       x=CurrentX;
       y=CurrentY;
       XYpair nextStep=path.getFirst();
       CurrentX=nextStep.x;
       CurrentY=nextStep.y;
    }
}

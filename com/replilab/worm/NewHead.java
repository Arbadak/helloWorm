package com.replilab.worm;

import com.replilab.worm.ai.CoordinateObserver;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NewHead implements Actor {

    private Map<String, Image> imgRes = new ConcurrentHashMap<>();
    private int CurrentY, CurrentX;
    private int x, y; //Координаты до пересчета нужны для отправки цепочке звеньев
    private Deque<String> headLook = new ArrayDeque<>();//Направление движения смейки
    private NewCell childCell;
    private Food food;
    private String event;
    private final GraphicsContext gc;
    //private CoordinateObserver aiAware;
    Set onScreenObjectSet;



    public NewHead(Food food, GraphicsContext gc, Set onScreenObjectSet) {
       // this.aiAware = aiAware;
        CurrentX = (int) (Math.random() * 20) * 30 + 180;
        CurrentY = (int) (Math.random() * 26) * 30 + 180;
        this.gc=gc;
        graphicResourseLoader();
        this.food = food;
        headLook.addAll(Arrays.asList("UP", "LEFT", "DOWN", "RIGHT"));
        this.onScreenObjectSet=onScreenObjectSet;
        onScreenObjectSet.add(this);
     }

    public void changeDirection(String direction) {

        if (direction == "LEFT") {
            headLook.addLast(headLook.pollFirst());
        }
        if (direction == "RIGHT") {
            headLook.addFirst(headLook.pollLast());
        }
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

    private void reposition() {
        x = CurrentX;
        y = CurrentY;

             switch (headLook.peekFirst()) {
                case "UP":
                    CurrentY = CurrentY - 30;
                    break;
                case "DOWN":
                    CurrentY = CurrentY + 30;
                    break;
                case "LEFT":
                    CurrentX = CurrentX - 30;
                    break;
                case "RIGHT":
                    CurrentX = CurrentX + 30;
                    break;
            }
    }

    private void drawSelf(GraphicsContext gc) {
        gc.drawImage(imgRes.get(headLook.peek()), CurrentX + 1, CurrentY + 1);
    }

    private void graphicResourseLoader() {
        imgRes.put("UP", new Image(getClass().getResourceAsStream("headup.bmp")));
        imgRes.put("DOWN", new Image(getClass().getResourceAsStream("headdown.bmp")));
        imgRes.put("LEFT", new Image(getClass().getResourceAsStream("headleft.bmp")));
        imgRes.put("RIGHT", new Image(getClass().getResourceAsStream("headright.bmp")));
        imgRes.put("CELL", new Image(getClass().getResourceAsStream("cell.bmp")));
    }


}
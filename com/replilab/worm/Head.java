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
import javafx.scene.input.KeyCode;

public class Head implements Actor {

    private int cellSize;
    private Map<String, Image> imgRes = new ConcurrentHashMap<>();
    private int CurrentY, CurrentX;
    private int x, y; //Координаты до пересчета нужны для отправки цепочке звеньев
    private Deque<DirectionArrow> headDirection = new ArrayDeque<>();//Направление движения смейки
    private Cell childCell;
    private Food food;
    private String event;
    private final GraphicsContext gc;
    //private CoordinateObserver aiAware;
    Set onScreenObjectSet;
    CoordinateObserver observer;


    public Head(Food food, GraphicsContext gc, Set onScreenObjectSet, CoordinateObserver observer) {
        // this.aiAware = aiAware;
        CurrentX = (int) (Math.random() * 20) * 30 + 180;
        CurrentY = (int) (Math.random() * 26) * 30 + 180;
        this.gc = gc;
        graphicResourceLoader();
        this.food = food;
        headDirection.addAll(Arrays.asList(DirectionArrow.UP, DirectionArrow.LEFT, DirectionArrow.DOWN, DirectionArrow.RIGHT));
        this.onScreenObjectSet = onScreenObjectSet;
        onScreenObjectSet.add(this);
        this.observer = observer;
    }

    public void changeDirection(KeyCode direction) {

        if (direction == KeyCode.LEFT) {
            headDirection.addLast(headDirection.pollFirst());
        }
        if (direction == KeyCode.RIGHT) {
            headDirection.addFirst(headDirection.pollLast());
        }
    }

    public void work() {

        reposition();
        drawSelf(gc);
        checkCollide();

        if (event == "grow" && childCell == null) {
            childCell = new Cell(gc, x, y, imgRes.get("CELL"), onScreenObjectSet, observer);
        } else if (childCell != null) {
            childCell.work(x, y, event);
        }
        observer.update(this, CurrentX, CurrentY);
    }

    private void checkCollide() {
        if (food.doesIhitYou(CurrentX, CurrentY) == true) {
            event = "grow";
        } else event = "tick";
    }

    private void reposition() {
        x = CurrentX;
        y = CurrentY;

        switch (headDirection.peekFirst()) {
            case UP:
                CurrentY = CurrentY - 30;
                break;
            case DOWN:
                CurrentY = CurrentY + 30;
                break;
            case LEFT:
                CurrentX = CurrentX - 30;
                break;
            case RIGHT:
                CurrentX = CurrentX + 30;
                break;
        }
    }

    private void drawSelf(GraphicsContext gc) {
        gc.drawImage(imgRes.get(headDirection.peek().name()), CurrentX + 1, CurrentY + 1);
    }

    private void graphicResourceLoader() {
        imgRes.put("UP", new Image(getClass().getResourceAsStream("resource/headup.bmp")));
        imgRes.put("DOWN", new Image(getClass().getResourceAsStream("resource/headdown.bmp")));
        imgRes.put("LEFT", new Image(getClass().getResourceAsStream("resource/headleft.bmp")));
        imgRes.put("RIGHT", new Image(getClass().getResourceAsStream("resource/headright.bmp")));
        imgRes.put("CELL", new Image(getClass().getResourceAsStream("resource/cell.bmp")));
    }


}
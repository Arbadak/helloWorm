package com.replilab.worm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NewHead {

    private Map<String, Image> imgRes = new ConcurrentHashMap<>();
    private StatBar statBar;
    private int CurrentY, CurrentX;
    private int x, y; //Координаты до пересчета нужны для отправки цепочке звеньев
    private Deque<String> headLook = new ArrayDeque<>();//Направление движения смейки
    private NewCell childCell;
    private Food food;
    private String event;
    private boolean isAuto;
    private Autopilot pilot;
    private  int careDistance = 60;
    private boolean collidePredicted;

    public NewHead(Food food, StatBar statBar, boolean type) {
        CurrentX = (int) (Math.random() * 20) * 30 + 180;
        CurrentY = (int) (Math.random() * 26) * 30 + 180;
        graphicResourseLoader();
        this.food = food;
        headLook.addAll(Arrays.asList("UP", "LEFT", "DOWN", "RIGHT"));
        this.statBar = statBar;
        statBar.increment();
        /*if (this.isAuto = type == true) {
            this.pilot = new DumpRandom();
        }*/
        this.isAuto = type;
        this.pilot = new DumpRandom();

    }

    public void changeDirection(String direction) {

        if (direction == "LEFT") {
            headLook.addLast(headLook.pollFirst());
        }
        if (direction == "RIGHT") {
            headLook.addFirst(headLook.pollLast());
        }

        if (direction == "DOWN") {
            isAuto=!isAuto;
        }
    }

    public void work(GraphicsContext gc) {
        if (isAuto) {
            changeDirection(pilot.direction());
        }
        reposition();
        drawSelf(gc);
        checkCollide();

        if (event == "grow" && childCell == null) {
            childCell = new NewCell(gc, x, y, imgRes.get("CELL"));
        } else if (childCell != null) {
            childCell.work(gc, x, y, event);
        }
    }

    private void checkCollide() {
        if (food.doesIbiteYou(CurrentX, CurrentY) == true) {
            event = "grow";
            statBar.increment();
        } else event = "tick";
    }

    private void reposition() {
        x = CurrentX;
        y = CurrentY;
        do {
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
            collidePredicted = (CurrentY < careDistance || CurrentX < careDistance || CurrentX > 820 - careDistance || CurrentY > 820 - careDistance);
            if (collidePredicted == true && isAuto == true) {
                changeDirection(pilot.direction());
                CurrentX = x;
                CurrentY = y;
            }
        } while (collidePredicted == true && isAuto == true);

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
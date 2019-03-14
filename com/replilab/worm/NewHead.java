package com.replilab.worm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NewHead  {

    private Image imageCell;
    private Image imageHeadUp;
    private Image imageHeadDowm;
    private Image imageHeadLeft;
    private Image imageHeadRigh;
    private int CurrentY, CurrentX;
    private int x,y; //Координаты до пересчета нужны для отправки цепочке звеньев
    private String headLook; //Направление движения смейки
    private NewCell childCell;
    private Food food;
    private  String event;


    public NewHead() {
        CurrentX = (int) (Math.random() * 26)*30;
        CurrentY = (int) (Math.random() * 26)*30;
        imageHeadUp = new Image(getClass().getResourceAsStream("headup.bmp"));
        imageHeadDowm = new Image(getClass().getResourceAsStream("headdown.bmp"));
        imageHeadLeft = new Image(getClass().getResourceAsStream("headleft.bmp"));
        imageHeadRigh = new Image(getClass().getResourceAsStream("headright.bmp"));
        food=new Food();
        headLook="UP";
    }

    public void changeDirection(String direction) {
        headLook = direction;
    }

    public void work(GraphicsContext gc) {

        reposition();
        drawSelf(gc);
        checkCollide();

        if (event == "grow" && childCell == null) {
            childCell = new NewCell(gc, x, y);
        } else if (childCell!=null){
            childCell.work(gc, x, y, event);
        }
    }

    private void checkCollide(){
        if (food.doesIbiteYou(CurrentX,CurrentY)==true) event="grow";
        else event="tick";
    }

    private void reposition() {
        x=CurrentX;
        y=CurrentY;
        switch (headLook) {
            case "UP":
                CurrentY = CurrentY - 30;
                imageCell=imageHeadUp;
                break;
            case "DOWN":
                CurrentY = CurrentY + 30;
                imageCell=imageHeadDowm;
                break;
            case "LEFT":
                CurrentX = CurrentX - 30;
                imageCell=imageHeadLeft;
                break;
            case "RIGHT":
                CurrentX = CurrentX + 30;
                imageCell=imageHeadRigh;
                break;
        }
    }
    private void drawSelf(GraphicsContext gc) {
        food.work(gc); //ДА Я ЗНАЮ ЧТО ТАК НЕПРАВИЛЬНО!
        gc.drawImage(imageCell, CurrentX+1, CurrentY+1);

    }
}
package com.replilab.worm;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.HashMap;

/**
 * worm class contain cellsHashMap
 */
@Deprecated
public class Worm implements Serializable {
    Image imageCell = new Image(getClass().getResourceAsStream("cell.bmp"));
    Image imageFood = new Image(getClass().getResourceAsStream("food.bmp"));
    Image imageHeadUp=new Image(getClass().getResourceAsStream("headup.bmp"));
    Image imageHeadDowm=new Image(getClass().getResourceAsStream("headdown.bmp"));
    Image imageHeadLeft=new Image(getClass().getResourceAsStream("headleft.bmp"));
    Image imageHeadRight=new Image(getClass().getResourceAsStream("headright.bmp"));



    public HashMap<Integer, Cells> cellsHashMap = new HashMap<Integer, Cells>();


    /**
     * Init Method, place randomly head of worm and food
     */

    public void begin() {


        Cells cell0 = new Cells();
        cell0.posX = 100;
        cell0.posY = 70;
        cell0.cellColor = "BLUE";
        cell0.id = 0;
        cell0.isHead=false;
        cellsHashMap.put(cell0.id, cell0);

        Cells cell = new Cells();
        cell.posX = (int) (Math.random() * 26)*30;
        cell.posY = (int) (Math.random() * 26)*30;

        cell.cellColor = "GREEN";
        cell.id = 1;
        cell.isHead=true;
        cell.headLook="RIGHT";
        cellsHashMap.put(cell.id, cell);
    }

    /**
     * Render all cellsHashMap in cellsHashMap collection according its coordinates
     *
     * @param gc - graphic context from main class
     */
    public void render(GraphicsContext gc){

      gc.clearRect(0,0,800,800);

        Image image;
        int maxI= cellsHashMap.size();
        for (int curentCellIndex=0; curentCellIndex<maxI/*+1*/;curentCellIndex++ ){
            image=imageCell;
            if (cellsHashMap.get(curentCellIndex).isHead) {

                    if (cellsHashMap.get(curentCellIndex).headLook=="UP"){image=imageHeadUp;}
                    if (cellsHashMap.get(curentCellIndex).headLook=="DOWN"){image=imageHeadDowm;}
                    if (cellsHashMap.get(curentCellIndex).headLook=="LEFT"){image=imageHeadLeft;}
                    if (cellsHashMap.get(curentCellIndex).headLook=="RIGHT"){image=imageHeadRight;}
            }
                if (cellsHashMap.get(curentCellIndex).id==0){image=imageFood;}

            gc.drawImage(image, cellsHashMap.get(curentCellIndex).posX+1, cellsHashMap.get(curentCellIndex).posY+1);
        }

        //for (Cells currentCell :Cells )


    }

    /**
     * Method increace worm by 1 cell if food is eated.
     */

    public void addCell(){

        int headIndex=1;
        for (int i = 1; cellsHashMap.get(i).isHead==false; i++){headIndex=i+1;}

        Cells newcell = new Cells();
        Cells oldcell= cellsHashMap.get(headIndex);
        switch (oldcell.headLook){
            case "UP" : newcell.posY=oldcell.posY-30; newcell.posX=oldcell.posX;break;
            case "DOWN" : newcell.posY=oldcell.posY+30; newcell.posX=oldcell.posX;break;
            case "LEFT" : newcell.posX=oldcell.posX-30; newcell.posY=oldcell.posY;break;
            case "RIGHT" : newcell.posX=oldcell.posX+30; newcell.posY=oldcell.posY;break;
            }
        newcell.cellColor = "PALE";
        newcell.id = headIndex+1;
        newcell.isHead=true;
        newcell.headLook=oldcell.headLook;
        cellsHashMap.put(newcell.id, newcell);
        oldcell.isHead=false;
        cellsHashMap.put(oldcell.id, oldcell);
    }

    /**
     * Method change worm's head direction according pressed keys
     * @param direction is pressed key, L/R/U/D
     */
    public void changeDirection(String direction){

        int headIndex=0;
        for (int i = 0; cellsHashMap.get(i).isHead==false; i++){headIndex=i+1;}

        Cells oldCell= cellsHashMap.get(headIndex);
        oldCell.headLook=direction;
        cellsHashMap.put(oldCell.id, oldCell);
    }

    /**
     * Method iterate all worm cell and shift its position by one cell according its heading
     * called by timer
     */
    public void makeStep(){

        int maxI= cellsHashMap.size();
        for (int currentCellIndex=1; currentCellIndex<maxI;currentCellIndex++ ) {
            int  aheadCellIndex=currentCellIndex+1;
            if(cellsHashMap.get(currentCellIndex).isHead!=true){

                cellsHashMap.get(currentCellIndex).posX= cellsHashMap.get(aheadCellIndex).posX;
                cellsHashMap.get(currentCellIndex).posY= cellsHashMap.get(aheadCellIndex).posY;
                cellsHashMap.get(currentCellIndex).headLook= cellsHashMap.get(aheadCellIndex).headLook;
            }
            else {
               switch (cellsHashMap.get(currentCellIndex).headLook) {
                   case "UP":
                       cellsHashMap.get(currentCellIndex).posY = cellsHashMap.get(currentCellIndex).posY - 30;
                       cellsHashMap.get(currentCellIndex).posX = cellsHashMap.get(currentCellIndex).posX;
                       break;
                   case "DOWN":
                       cellsHashMap.get(currentCellIndex).posY = cellsHashMap.get(currentCellIndex).posY + 30;
                       cellsHashMap.get(currentCellIndex).posX = cellsHashMap.get(currentCellIndex).posX;
                       break;
                   case "LEFT":
                       cellsHashMap.get(currentCellIndex).posX = cellsHashMap.get(currentCellIndex).posX - 30;
                       cellsHashMap.get(currentCellIndex).posY = cellsHashMap.get(currentCellIndex).posY;
                       break;
                   case "RIGHT":
                       cellsHashMap.get(currentCellIndex).posX = cellsHashMap.get(currentCellIndex).posX + 30;
                       cellsHashMap.get(currentCellIndex).posY = cellsHashMap.get(currentCellIndex).posY;
                       break;
               }
                if (cellsHashMap.get(currentCellIndex).posX== cellsHashMap.get(0).posX && cellsHashMap.get(currentCellIndex).posY== cellsHashMap.get(0).posY){foodRepositioning(); addCell();}
                 }
        }
    }

    /** Method spawn new food at random position
     *
     */
    public void foodRepositioning(){
        int foodX = (int) (Math.random() * 26)*30;
        int foodY = (int) (Math.random() * 26)*30;
        cellsHashMap.get(0).posX=foodX;
        cellsHashMap.get(0).posY=foodY;
    }

  }

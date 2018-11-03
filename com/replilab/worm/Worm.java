package com.replilab.worm;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * worm class contain cells
 */

public class Worm implements Serializable {
    Image imageCell = new Image(getClass().getResourceAsStream("cell.bmp"));
    Image imageFood = new Image(getClass().getResourceAsStream("food.bmp"));
    Image imageHeadUp=new Image(getClass().getResourceAsStream("headup.bmp"));
    Image imageHeadDowm=new Image(getClass().getResourceAsStream("headdown.bmp"));
    Image imageHeadLeft=new Image(getClass().getResourceAsStream("headleft.bmp"));
    Image imageHeadRight=new Image(getClass().getResourceAsStream("headright.bmp"));

    /**
     * Cell class, is element of worm, contains every cell coordinate and heading
     */

    public class Cells {
        public Integer id;
        public int posX, posY;
        public String cellColor;
        public Boolean isHead;
        public String headLook;
    }

    public HashMap<Integer, Cells> cells = new HashMap<Integer, Cells>();
    //public ArrayList<Object> cells = new ArrayList<Object>();


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
        cells.put(cell0.id, cell0);

        Cells cell = new Cells();
        cell.posX = (int) (Math.random() * 26)*30;
        cell.posY = (int) (Math.random() * 26)*30;

        cell.cellColor = "GREEN";
        cell.id = 1;
        cell.isHead=true;
        cell.headLook="RIGHT";
        cells.put(cell.id, cell);
    }

    /**
     * Render all cells in cells collection according its coordinates
     *
     * @param gc - graphic context from main class
     */
    public void render(GraphicsContext gc){

      gc.clearRect(0,0,800,800);

        Image image;
        int maxI=cells.size();
        for (int curentCellIndex=0; curentCellIndex<maxI/*+1*/;curentCellIndex++ ){
            image=imageCell;
            if (cells.get(curentCellIndex).isHead) {

                    if (cells.get(curentCellIndex).headLook=="UP"){image=imageHeadUp;}
                    if (cells.get(curentCellIndex).headLook=="DOWN"){image=imageHeadDowm;}
                    if (cells.get(curentCellIndex).headLook=="LEFT"){image=imageHeadLeft;}
                    if (cells.get(curentCellIndex).headLook=="RIGHT"){image=imageHeadRight;}
            }
                if (cells.get(curentCellIndex).id==0){image=imageFood;}

            gc.drawImage(image,cells.get(curentCellIndex).posX+1,cells.get(curentCellIndex).posY+1);
        }

        //for (Cells currentCell :Cells )


    }

    /**
     * Method increace worm by 1 cell if food is eated.
     */

    public void addCell(){

        int headIndex=1;
        for (int i=1; cells.get(i).isHead==false;i++){headIndex=i+1;}

        Cells newcell = new Cells();
        Cells oldcell=cells.get(headIndex);
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
        cells.put(newcell.id, newcell);
        oldcell.isHead=false;
        cells.put(oldcell.id, oldcell);
    }

    /**
     * Method change worm's head direction according pressed keys
     * @param direction is pressed key, L/R/U/D 
     */
    public void changeDirection(String direction){

        int headIndex=0;
        for (int i=0; cells.get(i).isHead==false;i++){headIndex=i+1;}

        Cells oldCell=cells.get(headIndex);
        oldCell.headLook=direction;
        cells.put(oldCell.id, oldCell);
    }

    /**
     * Method iterate all worm cell and shift its position by one cell according its heading
     * called by timer
     */
    public void makeStep(){

        int maxI=cells.size();
        for (int currentCellIndex=1; currentCellIndex<maxI;currentCellIndex++ ) {
            int  aheadCellIndex=currentCellIndex+1;
            if(cells.get(currentCellIndex).isHead!=true){

                cells.get(currentCellIndex).posX=cells.get(aheadCellIndex).posX;
                cells.get(currentCellIndex).posY=cells.get(aheadCellIndex).posY;
                cells.get(currentCellIndex).headLook=cells.get(aheadCellIndex).headLook;
            }
            else {
               switch (cells.get(currentCellIndex).headLook) {
                   case "UP":
                       cells.get(currentCellIndex).posY = cells.get(currentCellIndex).posY - 30;
                       cells.get(currentCellIndex).posX = cells.get(currentCellIndex).posX;
                       break;
                   case "DOWN":
                       cells.get(currentCellIndex).posY = cells.get(currentCellIndex).posY + 30;
                       cells.get(currentCellIndex).posX = cells.get(currentCellIndex).posX;
                       break;
                   case "LEFT":
                       cells.get(currentCellIndex).posX = cells.get(currentCellIndex).posX - 30;
                       cells.get(currentCellIndex).posY = cells.get(currentCellIndex).posY;
                       break;
                   case "RIGHT":
                       cells.get(currentCellIndex).posX = cells.get(currentCellIndex).posX + 30;
                       cells.get(currentCellIndex).posY = cells.get(currentCellIndex).posY;
                       break;
               }
                if (cells.get(currentCellIndex).posX==cells.get(0).posX && cells.get(currentCellIndex).posY==cells.get(0).posY){foodRepositioning(); addCell();}
                 }
        }
    }

    /** Method spawn new food at random position
     *
     */
    public void foodRepositioning(){
        int foodX = (int) (Math.random() * 26)*30;
        int foodY = (int) (Math.random() * 26)*30;
        cells.get(0).posX=foodX;
        cells.get(0).posY=foodY;
    }

  }

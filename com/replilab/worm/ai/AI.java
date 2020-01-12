package com.replilab.worm.ai;

import java.util.Arrays;
import java.util.Deque;
/*
public class AI implements Autopilot {
    private int foodX, foodY;


    @Override
    public String direction() {
        return null;
    }

    public String direction(int HeadX, int HeadY, String headlook) {

       // desirableDirection(x,y,headlook);

        return "";
    }

    public void notifyCoordFood(int x, int y){
        this.foodX=x;
        this.foodY=y;
    }

    private String desirableDirection(int HeadX, int HeadY, Deque headlook ){

        String vector1="";
        String vector2="";
        //calculate desired direction
        if (foodX> HeadX)vector1="LEFT";
        if (foodX< HeadX)vector1="RRIGHT";
        if (foodY> HeadY)vector2="UP";
        if (foodY< HeadY)vector2="DOWN";
        String cwd[]= {"UP","LEFT","DOWN","RIGHT","UP","LEFT","DOWN","RIGHT"};
        int begin=0;
        int finishVector1=0;
        for (int i=0;i<7;i++){
            if (cwd[i].equals(headlook))begin=i;
            if(cwd[i].equals(vector1)){
                finishVector1=i-begin;
                break;
            }
        }
        String ccwd[]= {"UP","RIGHT","DOWN","LEFT","UP","RIGHT","DOWN","LEFT"};
        begin=0;
        int finishVector2=0;
        for (int i=0;i<7;i++) {
            if (ccwd[i].equals(headlook)) begin = i;
            if (ccwd[i].equals(vector2)) {
                finishVector2 = i - begin;
                break;
            }
        }

        if (finishVector1<finishVector2) return
    }


    public String contra(String argument){
        if (argument.equals("LEFT"))return "RIGHT";
        if (argument.equals("RIGHT"))return "LEFT";
        if (argument.equals("UP"))return "DOWN";
        return "UP";
    }

}*/

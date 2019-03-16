package com.replilab.worm;

public class AI implements Autopilot {
    private int foodX, foodY;


    @Override
    public String direction() {
        return null;
    }

    public String direction(int HeadX, int HeadY) {
        return null;
    }

    public void foodPositionAware(int x, int y){
        this.foodX=x;
        this.foodY=y;
    }

}

package com.replilab.worm;

public class DumpRandom implements Autopilot {
    @Override
    public String direction() {
        String decision="";
        double chaceTochange=Math.random()*3;
        if (chaceTochange>2){
            decision=Math.random()>0.5? "LEFT":"RIGHT";
        }

        return decision;
    }
}

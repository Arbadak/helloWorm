package com.replilab.worm.ai;

import com.replilab.worm.Food;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MapBuilder {

    private Queue<XYpair> frontier;
   private Map<XYpair, XYpair> cameFrom;
   private XYpair foodCoord;

    public MapBuilder() {
        this.foodCoord = new XYpair(Food.CurrentX,Food.CurrentY);
    }

    public List<XYpair>calculatePath(XYpair currentPosition){
    buildAmap(currentPosition);
    List path=buildPath(foodCoord, currentPosition);
    return path;

    }

    private void buildAmap(XYpair start) {
        frontier = new LinkedBlockingQueue<>();
        frontier.add(start);
        cameFrom = new HashMap<>();
        cameFrom.put(start, null);
        XYpair current;

        while (!frontier.isEmpty()) {
            current = frontier.poll();
            List <XYpair> neighborsList=getNeiborgh(current);
            for (XYpair next : neighborsList ) {

                if (!cameFrom.containsKey(next)) {
                    frontier.add(next);
                    cameFrom.put(next, current);
                }
            }
        }
    }

    private List<XYpair> getNeiborgh(XYpair currentPoint) {

        List<XYpair> neighbors = new ArrayList();
        neighbors.add(new XYpair(currentPoint.x, currentPoint.y + 30));
        neighbors.add(new XYpair(currentPoint.x, currentPoint.y - 30));
        neighbors.add(new XYpair(currentPoint.x + 30, currentPoint.y));
        neighbors.add(new XYpair(currentPoint.x - 30, currentPoint.y));
        return neighbors;
    }

    private LinkedList<XYpair> buildPath(XYpair goal, XYpair start) {
        XYpair current = goal;
        LinkedList<XYpair> reversePath = new LinkedList<>();
        reversePath.add(current);
        while (current != start) {
            current = cameFrom.get(current);
            reversePath.add(current);
        }
        reversePath.add(start);
        LinkedList<XYpair> path = new LinkedList<>();
        Iterator<XYpair> iter = reversePath.descendingIterator();
        while (iter.hasNext()) {
            path.add(iter.next());
        }
        return path;
    }
}
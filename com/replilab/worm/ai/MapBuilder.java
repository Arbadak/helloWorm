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

public class MapBuilder {

    private Queue<XYpair> frontier;
   private Map<XYpair, XYpair> cameFrom;
   private XYpair foodCoord;

    public MapBuilder() {
        this.foodCoord = new XYpair(Food.CurrentX,Food.CurrentY);
    }

    public List<XYpair>calculatePath(XYpair currentPosition){
    buildAmap(currentPosition);
    return buildPath(foodCoord, currentPosition);

    }

    private void buildAmap(XYpair start) {
        frontier = new PriorityQueue<>();
        frontier.add(start);
        cameFrom = new HashMap<>();
        cameFrom.put(start, null);
        XYpair current;

        while (!frontier.isEmpty()) {
            current = frontier.poll();
            for (XYpair next : getNeiborgh(current)) {

                if (!cameFrom.containsKey(next)) {
                    frontier.add(next);
                    cameFrom.put(next, current);
                }
            }
        }
    }

    private List<XYpair> getNeiborgh(XYpair currentPoint) {

        List<XYpair> neighbors = new ArrayList();
        neighbors.add(new XYpair(currentPoint.x, currentPoint.y + 1));
        neighbors.add(new XYpair(currentPoint.x, currentPoint.y - 1));
        neighbors.add(new XYpair(currentPoint.x + 1, currentPoint.y));
        neighbors.add(new XYpair(currentPoint.x - 1, currentPoint.y));
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
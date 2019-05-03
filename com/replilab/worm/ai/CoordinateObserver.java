package com.replilab.worm.ai;

import java.util.HashMap;
import java.util.Map;

public class CoordinateObserver {
    Map<String, XYpair> objectsMap = new HashMap<>();

    public void update(String type, int x, int y) {
        objectsMap.put(type, new XYpair(x, y));
    }

    public void update(String type, int oldX, int oldY, int x, int y) {
        objectsMap.replace(type, new XYpair(oldX, oldY), new XYpair(x, y));
    }

    public void delete(String type, int x, int y) {
        objectsMap.remove(type, new XYpair(x, y));
    }
}

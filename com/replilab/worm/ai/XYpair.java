package com.replilab.worm.ai;

import java.util.Objects;

public class XYpair {
  public  int x,y;

    public XYpair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XYpair xYpair = (XYpair) o;
        return x == xYpair.x &&
                y == xYpair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

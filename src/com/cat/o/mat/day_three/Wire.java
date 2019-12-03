package com.cat.o.mat.day_three;

import java.util.ArrayList;
import java.util.List;

class Wire {
    private List<Cell> visited = new ArrayList<>();

    Wire() {
        this.visited.add(new Cell(0, 0));
    }

    List<Cell> getVisited() {
        return visited;
    }

    Cell getLastVisitedCell() {
        return visited.get(visited.size() - 1);
    }

}

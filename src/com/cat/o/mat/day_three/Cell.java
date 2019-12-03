package com.cat.o.mat.day_three;

public class Cell {

//    yCoord ^
//           |
//           |
//           |
//           +--------> xCoord

    private int xCoord;
    private int yCoord;

    Cell(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    int getxCoord() {
        return xCoord;
    }

    int getyCoord() {
        return yCoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (xCoord != cell.xCoord) return false;
        return yCoord == cell.yCoord;
    }
}

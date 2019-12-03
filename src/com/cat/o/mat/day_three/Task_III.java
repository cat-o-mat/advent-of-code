package com.cat.o.mat.day_three;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task_III {
    private static final String UP = "U";
    private static final String DOWN = "D";
    private static final String LEFT = "L";
    private static final String RIGHT = "R";

    private static final int MOVE_DIRECTION = 0;
    private static final int MOVE_DISTANCE = 1;


    private String input;
    private String[] wireInputs;
    private List<Wire> wires;
    private List<Cell> crossPoints;


    // 3.1
    public void calcDistance() {
        // resetting all values for the second task
        wires = new ArrayList<>();
        crossPoints = new ArrayList<>();

        readInput();
        addPathsToWires();
        getShortestDistance();
    }

    private void readInput() {
        try {
            input = Files.readString(Paths.get("src/com/cat/o/mat/day_three/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!input.isEmpty()) {
            wireInputs = input.split("\n");
        } else {
            System.out.println("No Input found.");
        }
    }


    private void addPathsToWires() {
        for (String path : wireInputs) {
            Wire wire = new Wire();
            Arrays.stream(path.strip().split(","))
                    .forEach(move -> {
                        String direction = String.valueOf(move.charAt(MOVE_DIRECTION));
                        int distance = Integer.parseInt(move.substring(MOVE_DISTANCE));
                        int xCoord = wire.getLastVisitedCell().getxCoord();
                        int yCoord = wire.getLastVisitedCell().getyCoord();
                        switch (direction.toUpperCase()) {
                            case UP:
                                for (int i = 1; i <= distance; i++) {
                                    wire.getVisited().add(new Cell(xCoord, yCoord + i));
                                }
                                break;
                            case DOWN:
                                for (int i = -1; i >= 0 - distance; i--) {
                                    wire.getVisited().add(new Cell(xCoord, yCoord + i));
                                }
                                break;
                            case LEFT:
                                for (int i = -1; i >= 0 - distance; i--) {
                                    wire.getVisited().add(new Cell(xCoord + i, yCoord));
                                }
                                break;
                            case RIGHT:
                                for (int i = 1; i <= distance; i++) {
                                    wire.getVisited().add(new Cell(xCoord + i, yCoord));
                                }
                                break;
                            default:
                                throw new RuntimeException("Not a valid direction: " + direction);
                        }
                    });

            wires.add(wire);
        }
    }

    private void getShortestDistance() {
        for (Cell cell : wires.get(0).getVisited()) {
            if (cell.getxCoord() != 0 && cell.getyCoord() != 0
                    && wires.get(1).getVisited().contains(cell)) {
                crossPoints.add(cell);
            }
        }

        int minDistance = crossPoints.stream()
                .map(c -> calcManhattanDistance(c.getxCoord(), c.getyCoord()))
                .reduce(Math::min)
                .orElse(0);

        System.out.println("Minimal disnace: " + minDistance);
        System.out.println("Correct answer is: 221");
    }


    private int calcManhattanDistance(int x, int y) {
        return Math.abs(x) + Math.abs(y);
    }


    // 3.2
    public void calcFewestSteps() {
        calcDistance();
        List<Integer> combinedDistances = new ArrayList<>();

        for (Cell crossPoint : crossPoints) {
            int distance = 0;
            for (Wire w : wires) {
                distance += w.getVisited().indexOf(crossPoint);
            }
            combinedDistances.add(distance);
        }

        if (!combinedDistances.isEmpty()) {
            Integer minCombinedSteps = combinedDistances.stream()
                    .reduce(Integer::min)
                    .orElse(0);
            System.out.println("What is the fewest combined steps the wires must take to reach an intersection? " + minCombinedSteps);
            System.out.println("Correct answer is: 18542");
        } else {
            System.out.println("No intersection found.");
        }
    }
}

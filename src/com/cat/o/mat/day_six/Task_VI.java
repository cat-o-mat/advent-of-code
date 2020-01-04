package com.cat.o.mat.day_six;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task_VI {

    private Graph graph = new Graph();
    private String input;
    private String[] foundOrbits;

    private void readInput() {
        try {
            input = Files.readString(Paths.get("src/com/cat/o/mat/day_six/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!input.isEmpty()) {
            foundOrbits = input.split("\n");
        } else {
            System.out.println("No Input found.");
        }
    }

    public void calcNumOfOrbits() {
        readInput();

        graph.addInputToGraph(foundOrbits);
        graph.countNumOfOrbits();
    }

}

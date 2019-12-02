package com.cat.o.mat.day_one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Task {

    public void calcFuelRequirements() {
        try {
            List<String> inputs = Files.readAllLines(Paths.get("src/com/cat/o/mat/day_one/input.txt"));
            int wholeWeight = inputs.stream()
                    .map(Integer::parseInt)
                    .map(i -> i / 3 - 2)
                    .reduce(Integer::sum)
                    .orElse(0);
            System.out.println("Day one:\nWhat is the sum of the fuel requirements for all of the modules on your spacecraft? " + wholeWeight);
            System.out.println("Correct answer is: 3305301");
        } catch (IOException e) {
            System.out.println("Oh noes.");
            e.printStackTrace();
        }
    }

}

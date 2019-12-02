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
            System.out.println("Day one_1:\nWhat is the sum of the fuel requirements for all of the modules on your spacecraft? " + wholeWeight);
            System.out.println("Correct answer is: 3305301");
        } catch (IOException e) {
            System.out.println("Oh noes.");
            e.printStackTrace();
        }
    }


    public void calcFuelRequirementsRec() {
        try {
            List<String> inputs = Files.readAllLines(Paths.get("src/com/cat/o/mat/day_one/input.txt"));
            int wholeWeight = inputs.stream()
                    .map(Integer::parseInt)
                    .map(this::calcFuelRec)
                    .reduce(Integer::sum)
                    .orElse(0);
            System.out.println("Day one_2:\nWhat is the sum of the fuel requirements for all of the modules on your spacecraft? " + wholeWeight);
            System.out.println("Correct answer is: 4955106");
        } catch (IOException e) {
            System.out.println("Oh noes.");
            e.printStackTrace();
        }
    }

    private int calcFuelRec(int mass) {
        int fuel = 0;
        // initial mass
        int additionalFuel = mass / 3 - 2;
        do {
            fuel += additionalFuel;
            additionalFuel = additionalFuel / 3 - 2;
            // additional fuel of max 8 does not require more fuel since
            // 8 / 3 - 2 = 0
            // 9 / 3 - 2 = 1
        } while (additionalFuel > 8);
        //adding the last calculated fuel
        if (additionalFuel > 0) {
            fuel += additionalFuel;
        }
        return fuel;
    }

}

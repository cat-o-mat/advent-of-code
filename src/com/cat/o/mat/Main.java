package com.cat.o.mat;

import com.cat.o.mat.day_one.Task_I;
import com.cat.o.mat.day_two.Task_II;

public class Main {

    public static void main(String[] args) {
        Task_I dayOne = new Task_I();
        dayOne.calcFuelRequirements();
        dayOne.calcFuelRequirementsRec();
        Task_II dayTwo = new Task_II();
        dayTwo.runIntcode();
    }
}


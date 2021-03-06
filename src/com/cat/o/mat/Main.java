package com.cat.o.mat;

import com.cat.o.mat.day_five.Task_V;
import com.cat.o.mat.day_four.Task_IV;
import com.cat.o.mat.day_one.Task_I;
import com.cat.o.mat.day_six.Task_VI;
import com.cat.o.mat.day_three.Task_III;
import com.cat.o.mat.day_two.Task_II;

public class Main {

    public static void main(String[] args) {
        Task_I dayOne = new Task_I();
        dayOne.calcFuelRequirements();
        dayOne.calcFuelRequirementsRec();

        Task_II dayTwo = new Task_II();
        dayTwo.runIntcode(12, 2);
        dayTwo.findInputs();

        Task_III dayThree = new Task_III();
        dayThree.calcDistance();
        dayThree.calcFewestSteps();

        Task_IV dayFour = new Task_IV();
        dayFour.countNumOfPswds();
        dayFour.countNumOfPswds_2();

        Task_V day_five = new Task_V();
        day_five.runIntcode();
        day_five.runIntcode_2();

        Task_VI day_six = new Task_VI();
        day_six.calcNumOfOrbits();
    }
}


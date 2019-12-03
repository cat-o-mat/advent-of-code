package com.cat.o.mat.day_two;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Task_II {

    private static final int HALT_CODE = 99;
    private static final int ADD_CODE = 1;
    private static final int MULT_CODE = 2;
    private static final int RESULT_IDX = 0;
    private static final int WANTED_RESULT = 19690720;

    private String input = "";
    private int[] intcode;

    private void readInput() {
        try {
            input = Files.readString(Paths.get("src/com/cat/o/mat/day_two/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!input.isEmpty()) {
            intcode = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } else {
            intcode = new int[0];
            System.out.println("No Input found.");
        }
    }

    public Integer runIntcode(Integer custLeftOp, Integer custRightOp) {
        readInput();

        if (intcode.length > 0) {
            int op = 0;
            int leftOp = 1;
            int rightOp = 2;
            int res = 3;

            if (custLeftOp != null) {
                intcode[1] = custLeftOp;
            }
            if (custRightOp != null) {
                intcode[2] = custRightOp;
            }

            while (intcode[op] != HALT_CODE) {
                int leftAddr = intcode[leftOp];
                int rightAddr = intcode[rightOp];
                int resAddr = intcode[res];
                if (intcode[op] == ADD_CODE) {
                    intcode[resAddr] = intcode[leftAddr] + intcode[rightAddr];
                } else if (intcode[op] == MULT_CODE) {
                    intcode[resAddr] = intcode[leftAddr] * intcode[rightAddr];
                } else {
                    throw new RuntimeException("Not an operand.");
                }
                op += 4;
                leftOp += 4;
                rightOp += 4;
                res += 4;
            }

            System.out.println("Result:" + intcode[RESULT_IDX]);
            System.out.println("Correct answer is: 3850704");
        } else {
            System.out.println("No input found.");
            return null;
        }
        return intcode[RESULT_IDX];
    }

    public void findInputs() {
        for (int leftOp = 0; leftOp < 99; leftOp++) {
            for (int rightOp = 0; rightOp < 99; rightOp++) {
                readInput();
                Integer foundResult = runIntcode(leftOp, rightOp);
                if (foundResult != null && foundResult == WANTED_RESULT) {
                    int res = 100 * leftOp + rightOp;

                    System.out.println("What is 100 * noun + verb? " + res);
                    System.out.println("Correct answer is: 6718");

                    return;
                }
            }
        }

        System.out.println("No possible solution.");
    }

}

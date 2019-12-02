package com.cat.o.mat.day_two;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Task_II {

    private static final int HALT_CODE = 99;
    private static final int ADD_CODE = 1;
    private static final int MULT_CODE = 2;

    public void runIntcode() {
        String input = "";
        try {
            input = Files.readString(Paths.get("src/com/cat/o/mat/day_two/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (input != null) {
            int[] intcode = Arrays.stream(input.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (intcode.length > 0) {
                int op = 0;
                int leftOp = 1;
                int rightOp = 2;
                int res = 3;

                intcode[1] = 12;
                intcode[2] = 2;

                while (intcode.length < op || intcode[op] != HALT_CODE) {
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

                System.out.println("Result:");
                System.out.println(
                        Arrays.stream(intcode)
                                .mapToObj(Integer::toString)
                                .collect(Collectors.joining(","))
                );
            } else {
                System.out.println("No Input found.");
            }
        }

    }

}

package com.cat.o.mat.day_five;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Task_V {

    private static final int ADD_CODE = 1;
    private static final int MULT_CODE = 2;
    // Takes a single integer as input and saves it to the position given by its only parameter.
    // For example, the instruction 3,50 would take an input value and store it at address 50.
    private static final int OPCODE_3 = 3;
    // Outputs the value of its only parameter.
    // For example, the instruction 4,50 would output the value at address 50.
    private static final int OPCODE_4 = 4;
    private static final int HALT_CODE = 99;

    // Interpreting the parameter as a position.
    // If the parameter is 50, its value is the value stored at address 50 in memory.
    private static final int POSITION_MOD = 0;
    // Interpreting the parameter as a value.
    // If the parameter is 50, its value is simply 50.
    private static final int IMMEDIATE_MOD = 1;

    private static final int HARDCODED_INPUT = 1;

    private String input = "";
    private int[] intcode;

    private void readInput() {
        try {
            input = Files.readString(Paths.get("src/com/cat/o/mat/day_five/input.txt"));
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


    public void runIntcode() {
        readInput();

        if (intcode.length > 0) {
            int idxOpcode = 0;
            int idxFstParam = 1;
            int idxSndParam = 2;
            int idxThrParam = 3;

            int opcode = getOpcode(intcode[idxOpcode]);

            while (opcode != HALT_CODE) {

                int fstParamMode;
                int sndParamMode;

                int fstParam;
                int sndParam;
                int thrParam;

                Integer fstActualParam;
                Integer sndActualParam;
                switch (opcode) {
                    case ADD_CODE:

                        fstParamMode = getModeOfFstParam(intcode[idxOpcode]);
                        sndParamMode = getModeOfSndParam(intcode[idxOpcode]);

                        fstParam = intcode[idxFstParam];
                        sndParam = intcode[idxSndParam];
                        thrParam = intcode[idxThrParam];

                        if (fstParamMode == POSITION_MOD) {
                            fstActualParam = intcode[fstParam];
                        } else if (fstParamMode == IMMEDIATE_MOD) {
                            fstActualParam = fstParam;
                        } else {
                            throw new RuntimeException("Unknown parameter mode (1): " + fstParamMode);
                        }

                        if (sndParamMode == POSITION_MOD) {
                            sndActualParam = intcode[sndParam];
                        } else if (sndParamMode == IMMEDIATE_MOD) {
                            sndActualParam = sndParam;
                        } else {
                            throw new RuntimeException("Unknown parameter mode (2): " + sndParamMode);
                        }

                        intcode[thrParam] = fstActualParam + sndActualParam;

                        idxOpcode += 4;
                        idxFstParam += 4;
                        idxSndParam += 4;
                        idxThrParam += 4;

                        opcode = getOpcode(intcode[idxOpcode]);

                        break;

                    case MULT_CODE:

                        fstParamMode = getModeOfFstParam(intcode[idxOpcode]);
                        sndParamMode = getModeOfSndParam(intcode[idxOpcode]);

                        fstParam = intcode[idxFstParam];
                        sndParam = intcode[idxSndParam];
                        thrParam = intcode[idxThrParam];

                        if (fstParamMode == POSITION_MOD) {
                            fstActualParam = intcode[fstParam];
                        } else if (fstParamMode == IMMEDIATE_MOD) {
                            fstActualParam = fstParam;
                        } else {
                            throw new RuntimeException("Unknown parameter mode (1): " + fstParamMode);
                        }

                        if (sndParamMode == POSITION_MOD) {
                            sndActualParam = intcode[sndParam];
                        } else if (sndParamMode == IMMEDIATE_MOD) {
                            sndActualParam = sndParam;
                        } else {
                            throw new RuntimeException("Unknown parameter mode (2): " + sndParamMode);
                        }

                        intcode[thrParam] = fstActualParam * sndActualParam;

                        idxOpcode += 4;
                        idxFstParam += 4;
                        idxSndParam += 4;
                        idxThrParam += 4;

                        opcode = getOpcode(intcode[idxOpcode]);

                        break;
                    case OPCODE_3:

                        fstParam = intcode[idxFstParam];

                        intcode[fstParam] = HARDCODED_INPUT;

                        idxOpcode += 2;
                        idxFstParam += 2;
                        idxSndParam += 2;
                        idxThrParam += 2;

                        opcode = getOpcode(intcode[idxOpcode]);

                        break;
                    case OPCODE_4:

                        fstParamMode = getModeOfFstParam(intcode[idxOpcode]);

                        fstParam = intcode[idxFstParam];

                        if (fstParamMode == POSITION_MOD) {
                            System.out.println(intcode[fstParam]);
                        } else if (fstParamMode == IMMEDIATE_MOD) {
                            System.out.println(intcode[idxFstParam]);
                        } else {
                            throw new RuntimeException("Unknown parameter mode (3): " + fstParamMode);
                        }

                        idxOpcode += 2;
                        idxFstParam += 2;
                        idxSndParam += 2;
                        idxThrParam += 2;

                        opcode = getOpcode(intcode[idxOpcode]);

                        break;
                    default:
                        throw new RuntimeException("No such opcode: " + opcode);
                }
            }
        } else {
            System.out.println("No input found.");
        }
    }


//    ABCDE
//     1002
//
//    DE - two-digit opcode,      02 == opcode 2
//    C - mode of 1st parameter,  0 == position mode
//    B - mode of 2nd parameter,  1 == immediate mode
//    A - mode of 3rd parameter,  0 == position mode, omitted due to being a leading zero
//    Parameters that an instruction writes to will never be in immediate mode -> the 3th parameter is always in mode 0.

    private int getOpcode(int command) {
        return command % 100;
    }

    private int getModeOfFstParam(int command) {
        int idxInCommand = 3;
        while (idxInCommand > 1) {
            command = command / 10;
            idxInCommand--;
        }
        return command % 10;
    }

    private int getModeOfSndParam(int command) {
        int idxInCommand = 4;
        while (idxInCommand > 1) {
            command = command / 10;
            idxInCommand--;
        }
        return command % 10;
    }

}

package com.cat.o.mat.day_four;

public class Task_IV {

    private static final int LOWER_BOUND = 193651;
    private static final int UPPER_BOUND = 649729;

    public void countNumOfPswds() {
        int count = 0;
        for (int i = LOWER_BOUND; i <= UPPER_BOUND; i++) {
            if (hasSameAjacentDigits(i) && hasOnlyIncrNumsers(i)) {
                count++;
            }
        }
        System.out.println("How many different passwords within the range given in your puzzle input meet these criteria? " + count);
    }

    // works in reverse: 193 => 3 9 1
    private boolean hasSameAjacentDigits(int num) {
        int curr = num % 10;
        num = num / 10;
        while (num > 0) {
            int pred = num % 10;
            if (curr == pred) {
                return true;
            }
            curr = pred;
            num = num / 10;
        }
        return false;
    }

    // works in reverse: 193 => 3 9 1
    private boolean hasOnlyIncrNumsers(int num) {
        int curr = num % 10;
        num = num / 10;
        while (num > 0) {
            int pred = num % 10;
            if (curr < pred) {
                System.out.println("false");
                return false;
            }
            curr = pred;
            num = num / 10;
        }
        System.out.println("true");
        return true;
    }

}

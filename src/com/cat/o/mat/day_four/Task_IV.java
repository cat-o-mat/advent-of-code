package com.cat.o.mat.day_four;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task_IV {

    private static final int LOWER_BOUND = 193651;
    private static final int UPPER_BOUND = 649729;

    public void countNumOfPswds() {
        System.out.println("How many different passwords within the range given in your puzzle input meet these criteria? " + countWith(v -> v >= 2));
    }

    public void countNumOfPswds_2() {
        System.out.println("How many different passwords within the range given in your puzzle input meet these criteria? " + countWith(v -> v == 2));
    }

    private long countWith(Predicate<Long> predicate) {
        return IntStream.rangeClosed(LOWER_BOUND, UPPER_BOUND)
                .filter(Task_IV::hasOnlyIncrNumbers)
                .filter(i ->
                        Integer.toString(i).chars()
                                .boxed()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                .values().stream()
                                    .anyMatch(predicate)
                ).count();
    }

    // works in reverse: 193 => 3 9 1
    private static boolean hasOnlyIncrNumbers(int num) {
        int curr = num % 10;
        num = num / 10;
        while (num > 0) {
            int pred = num % 10;
            if (curr < pred) {
                return false;
            }
            curr = pred;
            num = num / 10;
        }
        return true;
    }
}

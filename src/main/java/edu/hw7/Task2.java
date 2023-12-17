package edu.hw7;

import java.util.stream.LongStream;

public class Task2 {

    private Task2() {}

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число не должно быть отрицательным");
        }
        if (n == 0) {
            return 1;
        }
        return LongStream.range(1, n + 1).parallel().reduce((x, y) -> x * y).orElse(0);
    }
}

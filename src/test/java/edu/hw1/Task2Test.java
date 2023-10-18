package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task2Test {
    private static final Task2 TASK2 = new Task2();

    @ParameterizedTest
    @MethodSource("parameters")
    public void countDigitsTest(int n, int expected) {
        int actual = TASK2.countDigits(n);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parameters() {
        return Stream.of(
            Arguments.of(0, 1),
            Arguments.of(1, 1),
            Arguments.of(-1, 1),
            Arguments.of(10, 2),
            Arguments.of(-99, 2),
            Arguments.of(12345, 5),
            Arguments.of(-333, 3)
        );
    }

}

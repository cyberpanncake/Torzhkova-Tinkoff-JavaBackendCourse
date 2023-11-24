package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task2Test {

    @ParameterizedTest
    @MethodSource("params")
    void factorialTest(int n, long expected) {
        long actual = Task2.factorial(n);
        Assertions.assertEquals(expected, actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of(0, 1),
            Arguments.of(1, 1),
            Arguments.of(2, 2),
            Arguments.of(3, 6),
            Arguments.of(4, 24),
            Arguments.of(10, 3628800),
            Arguments.of(12, 479001600)
        );
    }

    @Test
    void incrementCounterTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task2.factorial(-1));
    }
}

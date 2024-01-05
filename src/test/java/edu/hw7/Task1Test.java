package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task1Test {

    @ParameterizedTest
    @MethodSource("params")
    void incrementCounterTest(int expected) throws InterruptedException {
        int actual = Task1.incrementCounter(expected);
        Assertions.assertEquals(expected, actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of(1),
            Arguments.of(2),
            Arguments.of(10),
            Arguments.of(67),
            Arguments.of(100)
        );
    }

    @Test
    void incrementCounterTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task1.incrementCounter(0));
    }
}

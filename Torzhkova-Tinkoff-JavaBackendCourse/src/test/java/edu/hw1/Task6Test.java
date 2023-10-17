package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task6Test {
    private static final Task6 TASK6 = new Task6();

    @ParameterizedTest
    @MethodSource("parameters")
    public void countKTest(int n, int expected) {
        int actual = TASK6.countK(n);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parameters() {
        return Stream.of(
            Arguments.of(3524, 3),
            Arguments.of(6621, 5),
            Arguments.of(6554, 4),
            Arguments.of(1234, 3),
            Arguments.of(1234, 3),
            Arguments.of(3871, 3)
        );
    }

    @Test
    public void countKNegativeExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK6.countK(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK6.countK(-1234));
    }

    @Test
    public void countKNot4DigitsExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK6.countK(123));
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK6.countK(12345));
    }

    @Test
    public void countKSameDigitsExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK6.countK(1111));
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK6.countK(5555));
    }
}

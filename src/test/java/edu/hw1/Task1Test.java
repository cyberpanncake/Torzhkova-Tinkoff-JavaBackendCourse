package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task1Test {
    private static final Task1 TASK1 = new Task1();

    @ParameterizedTest
    @MethodSource("parametersValid")
    public void minutesToSecondsValidTest(String n, int expected) {
        int actual = TASK1.minutesToSeconds(n);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersValid() {
        return Stream.of(
            Arguments.of("01:00", 60),
            Arguments.of("13:56", 836),
            Arguments.of("999:59", 59999)
        );
    }

    @ParameterizedTest
    @MethodSource("parametersInvalid")
    public void minutesToSecondsInvalidTest(String n, int expected) {
        int actual = TASK1.minutesToSeconds(n);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersInvalid() {
        return Stream.of(
            Arguments.of("10:60", -1),
            Arguments.of("abc", -1)
        );
    }

    @Test
    public void minutesToSecondsNullExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK1.minutesToSeconds(null));
    }
}

package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task5Test {
    private static final Task5 TASK5 = new Task5();

    @ParameterizedTest
    @MethodSource("parametersPalindromes")
    public void isPalindromeDescendantTrueTest(int n, boolean expected) {
        boolean actual = TASK5.isPalindromeDescendant(n);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersPalindromes() {
        return Stream.of(
            Arguments.of(11211230, true),
            Arguments.of(13001120, true),
            Arguments.of(23336014, true),
            Arguments.of(11, true)
        );
    }

    @ParameterizedTest
    @MethodSource("parametersNotPalindromes")
    public void isPalindromeDescendantFalseTest(int n, boolean expected) {
        boolean actual = TASK5.isPalindromeDescendant(n);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersNotPalindromes() {
        return Stream.of(
            Arguments.of(11000012, false),
            Arguments.of(41, false),
            Arguments.of(33601423, false)
        );
    }

    @Test
    public void isPalindromeDescendantExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK5.isPalindromeDescendant(12345));
    }
}

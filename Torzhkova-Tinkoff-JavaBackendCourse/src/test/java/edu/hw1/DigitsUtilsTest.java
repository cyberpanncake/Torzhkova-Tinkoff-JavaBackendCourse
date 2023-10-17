package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class DigitsUtilsTest {
    @ParameterizedTest
    @MethodSource("parametersNumbers")
    public void numberToDigitsTest(int n, List<Integer> expected) {
        List<Integer> actual = DigitsUtils.numberToDigits(n);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersNumbers() {
        return Stream.of(
            Arguments.of(1, new ArrayList<>(Arrays.asList(1))),
            Arguments.of(123, new ArrayList<>(Arrays.asList(1, 2, 3))),
            Arguments.of(501837, new ArrayList<>(Arrays.asList(5, 0, 1, 8, 3, 7))),
            Arguments.of(0, new ArrayList<>(Arrays.asList(0)))
        );
    }

    @Test
    public void numberToDigitsExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> DigitsUtils.numberToDigits(-1));
    }

    @ParameterizedTest
    @MethodSource("parametersDigits")
    public void digitsToNumberTest(List<Integer> digits, int expected) {
        int actual = DigitsUtils.digitsToNumber(digits);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersDigits() {
        return Stream.of(
            Arguments.of(new ArrayList<>(Arrays.asList(1)), 1),
            Arguments.of(new ArrayList<>(Arrays.asList(1, 2, 3)), 123),
            Arguments.of(new ArrayList<>(Arrays.asList(5, 0, 1, 8, 3, 7)), 501837),
            Arguments.of(new ArrayList<>(Arrays.asList(0)), 0)
        );
    }

    @Test
    public void digitsToNumberExceptionTest() {
        List<Integer> digits = new ArrayList<>(Arrays.asList(1, -2, 3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> DigitsUtils.digitsToNumber(digits));
    }
}

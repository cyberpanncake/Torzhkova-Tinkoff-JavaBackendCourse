package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task7Test {

    @ParameterizedTest
    @MethodSource("params1")
    void check1Test(String string, boolean expected) {
        boolean actual = Task7.check1(string);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params1() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("11011111111111", true),
            Arguments.of("01011", true),
            Arguments.of("1", false),
            Arguments.of("00", false),
            Arguments.of("001", false),
            Arguments.of("", false),
            Arguments.of("0a0", false)
        );
    }

    @Test
    void check1ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task7.check1(null));
    }

    @ParameterizedTest
    @MethodSource("params2")
    void check2Test(String string, boolean expected) {
        boolean actual = Task7.check2(string);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params2() {
        return Stream.of(
            Arguments.of("1", true),
            Arguments.of("0", true),
            Arguments.of("11", true),
            Arguments.of("00", true),
            Arguments.of("10101001011", true),
            Arguments.of("0101001010", true),
            Arguments.of("10", false),
            Arguments.of("01", false),
            Arguments.of("", false),
            Arguments.of("1a1", false)
        );
    }

    @Test
    void check2ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task7.check2(null));
    }

    @ParameterizedTest
    @MethodSource("params3")
    void check3Test(String string, boolean expected) {
        boolean actual = Task7.check3(string);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params3() {
        return Stream.of(
            Arguments.of("1", true),
            Arguments.of("000", true),
            Arguments.of("10", true),
            Arguments.of("", false),
            Arguments.of("1111", false),
            Arguments.of("1f", false)
        );
    }

    @Test
    void check3ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task7.check3(null));
    }
}

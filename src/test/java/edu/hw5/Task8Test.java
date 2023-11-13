package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task8Test {

    @ParameterizedTest
    @MethodSource("params1")
    void check1Test(String string, boolean expected) {
        boolean actual = Task8.check1(string);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params1() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("10", false),
            Arguments.of("1", true),
            Arguments.of("", false),
            Arguments.of("00101", true)
        );
    }

    @Test
    void check1ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task8.check1(null));
    }

    @ParameterizedTest
    @MethodSource("params2")
    void check2Test(String string, boolean expected) {
        boolean actual = Task8.check2(string);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params2() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("10", true),
            Arguments.of("1", false),
            Arguments.of("", false),
            Arguments.of("00", false)
        );
    }

    @Test
    void check2ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task8.check2(null));
    }

    @ParameterizedTest
    @MethodSource("params5")
    void check5Test(String string, boolean expected) {
        boolean actual = Task8.check5(string);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params5() {
        return Stream.of(
            Arguments.of("1", true),
            Arguments.of("10", true),
            Arguments.of("101", true),
            Arguments.of("111", true),
            Arguments.of("0", false),
            Arguments.of("01", false),
            Arguments.of("11100", false)
        );
    }

    @Test
    void check5ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task8.check5(null));
    }

    @ParameterizedTest
    @MethodSource("params7")
    void check7Test(String string, boolean expected) {
        boolean actual = Task8.check7(string);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params7() {
        return Stream.of(
            Arguments.of("", true),
            Arguments.of("1", true),
            Arguments.of("0", true),
            Arguments.of("10", true),
            Arguments.of("000101", true),
            Arguments.of("111", false),
            Arguments.of("01001100", false)
        );
    }

    @Test
    void check7ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task8.check7(null));
    }

}

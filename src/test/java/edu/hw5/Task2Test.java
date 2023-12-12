package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

class Task2Test {

    @ParameterizedTest
    @MethodSource("params1")
    void getFridays13InYearTest(int year, List<LocalDate> expected) {
        List<LocalDate> actual = Task2.getFridays13InYear(year);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params1() {
        return Stream.of(
            Arguments.of(1925, List.of(
                LocalDate.of(1925, 2, 13),
                LocalDate.of(1925, 3, 13),
                LocalDate.of(1925, 11, 13))),
            Arguments.of(2024, List.of(
                LocalDate.of(2024, 9, 13),
                LocalDate.of(2024, 12, 13)))
        );
    }

    @Test
    void getFridays13InYearExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task2.getFridays13InYear(Integer.MIN_VALUE));
    }

    @ParameterizedTest
    @MethodSource("params2")
    void getNextFriday13Test(LocalDate date, LocalDate expected) {
        LocalDate actual = Task2.getNextFriday13(date);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params2() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(1925, 2, 14),
                LocalDate.of(1925, 3, 13)),
            Arguments.of(
                LocalDate.of(2024, 9, 1),
                LocalDate.of(2024, 9, 13)),
            Arguments.of(
                LocalDate.of(2023, 12, 14),
                LocalDate.of(2024, 9, 13))
        );
    }

    @Test
    void getNextFriday13ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task2.getNextFriday13(null));
    }
}

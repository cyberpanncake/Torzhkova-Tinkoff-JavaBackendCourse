package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task1Test {

    @ParameterizedTest
    @MethodSource("params")
    void getAvgDurationTest(String[] input, String expected) {
        String actual = Task1.getAvgDuration(input);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of(new String[] {
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"
            }, "3ч 40м"),
            Arguments.of(new String[] {
                "2022-03-12, 20:20 - 2022-03-12, 23:50"
            }, "3ч 30м"),
            Arguments.of(new String[] {
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-03-13, 00:00 - 2022-03-13, 23:59",
                "2022-03-12, 18:30 - 2022-03-12, 19:30"
            }, "9ч 29м"),
            Arguments.of(new String[0], "0ч 0м")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidParams")
    void getAvgDurationExceptionTest(String[] input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task1.getAvgDuration(input));
    }

    private static Stream<Arguments> invalidParams() {
        return Stream.of(
            Arguments.of((Object) null),
            Arguments.of((Object) new String[] { null }),
            Arguments.of((Object) new String[] { "2022-03-12, 23:50 - 2022-03-12, 20:20"})
        );
    }
}

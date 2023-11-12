package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task5Test {

    @ParameterizedTest
    @MethodSource("params")
    void checkCarNumberTest(String n, boolean expected) {
        boolean actual = Task5.checkCarNumber(n);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВГ77", false),
            Arguments.of("А123ВЕ7777", false)
        );
    }

    @Test
    void checkCarNumberExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task5.checkCarNumber(null));
    }
}

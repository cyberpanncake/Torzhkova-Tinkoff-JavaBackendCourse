package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task4Test {

    @ParameterizedTest
    @MethodSource("params")
    void checkPasswordTest(String p, boolean expected) {
        boolean actual = Task4.checkPassword(p);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of("~!@#$%^&*|", true),
            Arguments.of("~pswd", true),
            Arguments.of("pswd!", true),
            Arguments.of("p@s&w$d", true),
            Arguments.of("pswd", false),
            Arguments.of("", false)
        );
    }

    @Test
    void checkPasswordExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task4.checkPassword(null));
    }
}

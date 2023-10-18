package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task4Test {
    private static final Task4 TASK4 = new Task4();

    @ParameterizedTest
    @MethodSource("parameters")
    public void isPalindromeDescendantTrueTest(String str, String expected) {
        String actual = TASK4.fixString(str);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parameters() {
        return Stream.of(
            Arguments.of("123456", "214365"),
            Arguments.of("hTsii  s aimex dpus rtni.g", "This is a mixed up string."),
            Arguments.of("badce", "abcde"),
            Arguments.of("", "")
        );
    }

    @Test
    public void fixStringNullExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK4.fixString(null));
    }
}

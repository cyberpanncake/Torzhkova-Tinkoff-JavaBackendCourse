package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task4Test {
    private static final Task4 TASK4 = new Task4();

    @ParameterizedTest
    @MethodSource("rightNumbers")
    public void convertToRomanRightTest(int n, String expected) {
        String actual = TASK4.convertToRoman(n);
        Assertions.assertEquals(expected, actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> rightNumbers() {
        return Stream.of(
            Arguments.of(1, "I"),
            Arguments.of(2, "II"),
            Arguments.of(4, "IV"),
            Arguments.of(10, "X"),
            Arguments.of(14, "XIV"),
            Arguments.of(19, "XIX"),
            Arguments.of(1547, "MDXLVII")
        );
    }

    @ParameterizedTest
    @MethodSource("wrongNumbers")
    public void convertToRomanExceptionTest(int n) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK4.convertToRoman(n));
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> wrongNumbers() {
        return Stream.of(
            Arguments.of(-1),
            Arguments.of(0),
            Arguments.of(-1547)
        );
    }
}

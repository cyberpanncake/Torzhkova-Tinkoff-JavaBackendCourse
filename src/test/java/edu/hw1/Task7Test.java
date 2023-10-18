package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task7Test {
    private static final Task7 TASK7 = new Task7();

    @ParameterizedTest
    @MethodSource("parametersRotateLeft")
    public void rotateLeftTest(int n, int shift, int expected) {
        int actual = TASK7.rotateLeft(n, shift);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersRotateLeft() {
        return Stream.of(
            Arguments.of(16, 1, 1),
            Arguments.of(17, 2, 6)
        );
    }

    @ParameterizedTest
    @MethodSource("parametersRotateRight")
    public void rotateRightTest(int n, int shift, int expected) {
        int actual = TASK7.rotateRight(n, shift);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersRotateRight() {
        return Stream.of(
            Arguments.of(8, 1, 4)
        );
    }

    @Test
    public void countKNegativeExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK7.rotateLeft(-1, 1));
    }
}

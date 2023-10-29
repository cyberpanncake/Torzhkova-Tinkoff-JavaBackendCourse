package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task2Test {
    private static final Task2 TASK2 = new Task2();

    @ParameterizedTest
    @MethodSource("rightBrackets")
    public void clusterizeRightTest(String input, String[] expected) {
        String[] actual = TASK2.clusterize(input);
        Assertions.assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> rightBrackets() {
        return Stream.of(
            Arguments.of("()()()", new String[] {"()", "()", "()"}),
            Arguments.of("((()))", new String[] {"((()))"}),
            Arguments.of("((()))(())()()(()())", new String[] {"((()))", "(())", "()", "()", "(()())"}),
            Arguments.of("((())())(()(()()))", new String[] {"((())())", "(()(()()))"})
        );
    }

    @ParameterizedTest
    @MethodSource("wrongBrackets")
    public void clusterizeWrongTest(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK2.clusterize(input));
    }

    private static Stream<Arguments> wrongBrackets() {
        return Stream.of(
            Arguments.of("()()()("),
            Arguments.of("(()))"),
            Arguments.of(")("),
            Arguments.of("abc"),
            Arguments.of("(1)")
        );
    }

    @Test
    public void clusterizeNullExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK2.clusterize(null));
    }
}

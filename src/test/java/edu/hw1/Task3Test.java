package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task3Test {
    private static final Task3 TASK3 = new Task3();

    @ParameterizedTest
    @MethodSource("parametersTrue")
    public void isNestableTrueTest(int[] a1, int[] a2, boolean expected) {
        boolean actual = TASK3.isNestable(a1, a2);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTrue() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {0, 6}, true),
            Arguments.of(new int[] {3, 1}, new int[] {4, 0}, true),
            Arguments.of(new int[] {}, new int[] {2, 3}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("parametersFalse")
    public void isNestableFalseTest(int[] a1, int[] a2, boolean expected) {
        boolean actual = TASK3.isNestable(a1, a2);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersFalse() {
        return Stream.of(
            Arguments.of(new int[] {9, 9, 8}, new int[] {8, 9}, false),
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {2, 3}, false),
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {}, false),
            Arguments.of(new int[] {}, new int[] {}, false)
        );
    }

    @Test
    public void isNestableNullExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK3.isNestable(null, new int[] {}));
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK3.isNestable(new int[] {}, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK3.isNestable(null, null));
    }
}

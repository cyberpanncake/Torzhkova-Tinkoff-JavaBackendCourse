package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task6Test {

    @ParameterizedTest
    @MethodSource("params")
    void checkSubstringTest(String string, String substring, boolean expected) {
        boolean actual = Task6.checkSubstring(string, substring);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of("achfdbaabgabcaabg", "abc", true),
            Arguments.of("substring", "string", true),
            Arguments.of("aacbxc", "abc", true),
            Arguments.of("abacaba", "x", false),
            Arguments.of("", "abc", false),
            Arguments.of("abc", "", true)
        );
    }

    @Test
    void checkSubstringExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task6.checkSubstring(null, null));
    }
}

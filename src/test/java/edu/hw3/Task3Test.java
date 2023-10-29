package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class Task3Test {

    @ParameterizedTest
    @MethodSource("frequencies")
    public void freqDictTest(List<?> input, Map expected) {
        Map actual = new Task3().freqDict(input);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> frequencies() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"),
                Map.of("bb", 2, "a", 2)),
            Arguments.of(List.of("this", "and", "that", "and"),
                Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(List.of("код", "код", "код", "bug"),
                Map.of("код", 3, "bug", 1)),
            Arguments.of(List.of(1, 1, 2, 2),
                Map.of(1, 2, 2, 2))
        );
    }
}

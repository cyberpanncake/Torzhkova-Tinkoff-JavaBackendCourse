package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task5Test {
    private static final Task5 TASK5 = new Task5();

    @ParameterizedTest
    @MethodSource("names")
    public void parseContactsTest(String[] input, String order, Task5.Contact[] expected) {
        Task5.Contact[] actual = TASK5.parseContacts(input, order);
        Assertions.assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> names() {
        return Stream.of(
            Arguments.of(new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC",
                new Task5.Contact[] {new Task5.Contact("Thomas Aquinas"),
                    new Task5.Contact("Rene Descartes"),
                    new Task5.Contact("David Hume"),
                    new Task5.Contact("John Locke")}),
            Arguments.of(new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC",
                new Task5.Contact[] {new Task5.Contact("Carl Gauss"),
                    new Task5.Contact("Leonhard Euler"),
                    new Task5.Contact("Paul Erdos")}),
            Arguments.of(new String[0], "DESC", new Task5.Contact[0]),
            Arguments.of(null, "DESC", new Task5.Contact[0])
        );
    }

    @Test
    public void parseContactsNullOrderExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK5.parseContacts(null, null));
    }

    @Test
    public void parseContactsWrongOrderExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TASK5.parseContacts(null, "ABACABA"));
    }
}

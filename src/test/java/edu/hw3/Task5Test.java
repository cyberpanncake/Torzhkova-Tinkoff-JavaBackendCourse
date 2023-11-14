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
                new Task5.Contact[] {Task5.Contact.of("Thomas Aquinas"),
                    Task5.Contact.of("Rene Descartes"),
                    Task5.Contact.of("David Hume"),
                    Task5.Contact.of("John Locke")}),
            Arguments.of(new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC",
                new Task5.Contact[] {Task5.Contact.of("Carl Gauss"),
                    Task5.Contact.of("Leonhard Euler"),
                    Task5.Contact.of("Paul Erdos")}),
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

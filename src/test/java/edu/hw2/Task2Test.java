package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle(2, 3)),
            Arguments.of(new Rectangle(2, 2)),
            Arguments.of(new Rectangle(20, 11)),
            Arguments.of(new Square(2)),
            Arguments.of(new Square(1)),
            Arguments.of(new Square(55))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void areaTest(Rectangle rect) {
        double expected = rect.getWidth() * rect.getHeight();
        double actual = rect.area();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rectangleCreationExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rectangle(-1, 0));
    }

    @Test
    void squareCreationExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Square(-1));
    }
}

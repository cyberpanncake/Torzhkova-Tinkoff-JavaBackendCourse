package edu.project2;

import edu.project2.maze.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Set;

class CellTest {

    @ParameterizedTest
    @MethodSource("cells")
    void cellCreationTest(int i, int j, Cell expected) {
        Cell actual = new Cell(i, j);
        Assertions.assertEquals(expected.i(), actual.i());
        Assertions.assertEquals(expected.j(), actual.j());
    }

    static Arguments[] cells() {
        return new Arguments[]{
            Arguments.of(0, 0, new Cell(0, 0)),
            Arguments.of(1, 0, new Cell(1, 0)),
            Arguments.of(11, 54, new Cell(11, 54))
        };
    }

    @ParameterizedTest
    @MethodSource("invalidCells")
    void cellCreationExceptionTest(int i, int j) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Cell(i, j));
    }

    static Arguments[] invalidCells() {
        return new Arguments[]{
            Arguments.of(-1, 0),
            Arguments.of(0, -1),
            Arguments.of(-100, -100)
        };
    }

    @RepeatedTest(100)
    void getRandomTest() {
        int n = 100;
        int m = 100;
        Cell cell = Cell.getRandom(n, m);
        Assertions.assertTrue(cell.i() >= 0 && cell.i() < n);
        Assertions.assertTrue(cell.j() >= 0 && cell.j() < m);
    }

    @ParameterizedTest
    @MethodSource("invalidSizes")
    void getRandomExceptionTest(int n, int m) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Cell.getRandom(n, m));
    }

    static Arguments[] invalidSizes() {
        return new Arguments[]{
            Arguments.of(0, 1),
            Arguments.of(1, 0),
            Arguments.of(-100, -100)
        };
    }

    @ParameterizedTest
    @MethodSource("neighbours")
    void getNeighboursTest(Cell cell, int n, int m, Set<Cell> expected) {
        Set<Cell> actual = Cell.getNeighbours(cell, n, m);
        Assertions.assertEquals(expected, actual);
    }

    static Arguments[] neighbours() {
        return new Arguments[]{
            Arguments.of(new Cell(0, 0), 100, 100, Set.of(
                new Cell(0, 1),
                new Cell(1, 0))),
            Arguments.of(new Cell(1, 0), 100, 100, Set.of(
                new Cell(0, 0),
                new Cell(1, 1),
                new Cell(2, 0))),
            Arguments.of(new Cell(0, 1), 100, 100, Set.of(
                new Cell(0, 0),
                new Cell(1, 1),
                new Cell(0, 2))),
            Arguments.of(new Cell(1, 1), 100, 100, Set.of(
                new Cell(0, 1),
                new Cell(1, 0),
                new Cell(1, 2),
                new Cell(2, 1)))
        };
    }

    @ParameterizedTest
    @MethodSource("invalidSizes")
    void getNeighboursExceptionTest(int n, int m) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Cell.getNeighbours(new Cell(0, 0), n, m));
    }
}

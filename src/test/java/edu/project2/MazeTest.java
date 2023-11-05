package edu.project2;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class MazeTest {

    @Test
    void mazeCreationTest() {
        int n = 20;
        int m = 50;
        Map<Cell, Set<Cell>> paths = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                paths.put(new Cell(i ,j), new HashSet<>());
            }
        }
        Assertions.assertDoesNotThrow(() -> new Maze(n, m, paths));
    }

    @ParameterizedTest
    @MethodSource("invalidMazes")
    void getNeighboursExceptionTest(int n, int m, Map<Cell, Set<Cell>> paths) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Maze(n, m, paths));
    }

    static Arguments[] invalidMazes() {
        return new Arguments[]{
            Arguments.of(0, 0, Collections.emptyMap()),
            Arguments.of(21, 51, Collections.emptyMap()),
            Arguments.of(1, 1, null),
            Arguments.of(1, 1, Collections.emptyMap()),
            Arguments.of(2, 2, Map.of(new Cell(0, 0), Collections.emptySet()))
        };
    }
}

package edu.project2;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.maze.generation.BinaryTreeMazeGenerator;
import edu.project2.maze.generation.DFSMazeGenerator;
import edu.project2.maze.generation.IMazeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MazeGeneratorTest {

    static Arguments[] generators() {
        return new Arguments[]{
            Arguments.of(new DFSMazeGenerator()),
            Arguments.of(new BinaryTreeMazeGenerator())
        };
    }

    @ParameterizedTest
    @MethodSource("generators")
    void generateTest(IMazeGenerator generator) {
        int n = 20;
        int m = 50;
        Maze maze = generator.generate(n, m);
        Assertions.assertEquals(n, maze.n());
        Assertions.assertEquals(m, maze.m());
        Assertions.assertNotNull(maze.paths());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Cell cell = new Cell(i, j);
                Assertions.assertTrue(maze.paths().containsKey(cell));
                Assertions.assertFalse(maze.paths().get(cell).isEmpty());
            }
        }
    }

    @ParameterizedTest
    @MethodSource("invalidMazeSizes")
    void generateExceptionTest(IMazeGenerator generator, int n, int m) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> generator.generate(n, m));
    }

    static Arguments[] invalidMazeSizes() {
        return new Arguments[]{
            Arguments.of(new DFSMazeGenerator(), 1, 0),
            Arguments.of(new DFSMazeGenerator(), 0, 1),
            Arguments.of(new DFSMazeGenerator(), 0, 0),
            Arguments.of(new DFSMazeGenerator(), 21, 50),
            Arguments.of(new DFSMazeGenerator(), 20, 51),
            Arguments.of(new DFSMazeGenerator(), 21, 51),
            Arguments.of(new BinaryTreeMazeGenerator(), 1, 0),
            Arguments.of(new BinaryTreeMazeGenerator(), 0, 1),
            Arguments.of(new BinaryTreeMazeGenerator(), 0, 0),
            Arguments.of(new BinaryTreeMazeGenerator(), 21, 50),
            Arguments.of(new BinaryTreeMazeGenerator(), 20, 51),
            Arguments.of(new BinaryTreeMazeGenerator(), 21, 51)
        };
    }
}

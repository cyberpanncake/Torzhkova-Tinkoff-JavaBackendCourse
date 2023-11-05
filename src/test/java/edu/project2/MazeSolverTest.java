package edu.project2;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.maze.generation.DFSMazeGenerator;
import edu.project2.maze.solution.BFSMazeSolver;
import edu.project2.maze.solution.DFSMazeSolver;
import edu.project2.maze.solution.IMazeSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MazeSolverTest {
    private static int N1;
    private static int M1;
    private static Maze normalMaze;
    private static Maze mazeWithNoPaths;

    @BeforeAll
    static void initialize() {
        N1 = 20;
        M1 = 50;
        normalMaze = new DFSMazeGenerator().generate(N1, M1);
        mazeWithNoPaths = new Maze(1, 2, Map.of(
            new Cell(0, 0), Collections.emptySet(),
            new Cell(0, 1), Collections.emptySet()
        ));
    }

    static Arguments[] solvers() {
        return new Arguments[]{
            Arguments.of(new DFSMazeSolver()),
            Arguments.of(new BFSMazeSolver())
        };
    }

    @ParameterizedTest
    @MethodSource("solvers")
    void solveTest(IMazeSolver solver) {
        for (int i = 0; i < 100; i++) {
            Cell startCell = Cell.getRandom(N1, M1);
            Cell endCell = Cell.getRandom(N1, M1);
            Assertions.assertTrue(solver.solve(normalMaze, startCell, endCell).size() > 0);
        }
    }

    @ParameterizedTest
    @MethodSource("solvers")
    void solveNoPathsMazeTest(IMazeSolver solver) {
        Cell startCell = new Cell(0, 0);
        Cell endCell = new Cell(0, 1);
        Assertions.assertFalse(solver.solve(mazeWithNoPaths, startCell, endCell).size() > 0);
    }

    @ParameterizedTest
    @MethodSource("solvers")
    void solveStartCellEqualsEndCellTest(IMazeSolver solver) {
        Cell startCell = new Cell(0, 0);
        Cell endCell = new Cell(0, 0);
        List<Cell> solutionNormalMaze = solver.solve(normalMaze, startCell, endCell);
        Assertions.assertTrue(solutionNormalMaze.size() == 1
            && solutionNormalMaze.get(0).equals(endCell));
        List<Cell> solutionMazeWithNoPaths = solver.solve(mazeWithNoPaths, startCell, endCell);
        Assertions.assertTrue(solutionMazeWithNoPaths.size() == 1
            && solutionMazeWithNoPaths.get(0).equals(endCell));
    }

    @ParameterizedTest
    @MethodSource("solvers")
    void solveStartCellExceptionTest(IMazeSolver solver) {
        Cell startCell = new Cell(N1 + 1, M1 + 1);
        Cell endCell = new Cell(0, 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> solver.solve(normalMaze, startCell, endCell));
    }

    @ParameterizedTest
    @MethodSource("solvers")
    void solveEndCellExceptionTest(IMazeSolver solver) {
        Cell startCell = new Cell(0, 0);
        Cell endCell = new Cell(N1 + 1, M1 + 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> solver.solve(normalMaze, startCell, endCell));
    }
}

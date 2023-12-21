package edu.hw9;

import edu.hw9.task3.ConcurrentDFSMazeSolver;
import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.maze.generation.DFSMazeGenerator;
import edu.project2.maze.solution.DFSMazeSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class Task3Test {
    private static int N1;
    private static int M1;
    private static Maze normalMaze;
    private static Maze mazeWithNoPaths;
    private static final ConcurrentDFSMazeSolver concurrentSolver = new ConcurrentDFSMazeSolver();
    private static final DFSMazeSolver solver = new DFSMazeSolver();

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

    @Test
    void solveTest() {
        for (int i = 0; i < 100; i++) {
            Cell startCell = Cell.getRandom(N1, M1);
            Cell endCell = Cell.getRandom(N1, M1);
            Assertions.assertEquals(solver.solve(normalMaze, startCell, endCell),
                concurrentSolver.solve(normalMaze, startCell, endCell));
        }
    }

    @Test
    void solveNoPathsMazeTest() {
        Cell startCell = new Cell(0, 0);
        Cell endCell = new Cell(0, 1);
        Assertions.assertEquals(solver.solve(normalMaze, startCell, endCell),
            concurrentSolver.solve(normalMaze, startCell, endCell));
    }

    @Test
    void solveStartCellEqualsEndCellTest() {
        Cell startCell = new Cell(0, 0);
        Cell endCell = new Cell(0, 0);
        List<Cell> solutionNormalMaze = concurrentSolver.solve(normalMaze, startCell, endCell);
        Assertions.assertTrue(solutionNormalMaze.size() == 1
            && solutionNormalMaze.get(0).equals(endCell));
        List<Cell> solutionMazeWithNoPaths = concurrentSolver.solve(mazeWithNoPaths, startCell, endCell);
        Assertions.assertTrue(solutionMazeWithNoPaths.size() == 1
            && solutionMazeWithNoPaths.get(0).equals(endCell));
    }

    @Test
    void solveStartCellExceptionTest() {
        Cell startCell = new Cell(N1 + 1, M1 + 1);
        Cell endCell = new Cell(0, 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> solver.solve(normalMaze, startCell, endCell));
    }

    @Test
    void solveEndCellExceptionTest() {
        Cell startCell = new Cell(0, 0);
        Cell endCell = new Cell(N1 + 1, M1 + 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> solver.solve(normalMaze, startCell, endCell));
    }
}

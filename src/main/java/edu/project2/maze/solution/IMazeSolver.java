package edu.project2.maze.solution;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import java.util.List;

public interface IMazeSolver {
    List<Cell> solve(Maze maze, Cell start, Cell end);
}

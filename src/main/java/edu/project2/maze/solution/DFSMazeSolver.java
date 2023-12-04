package edu.project2.maze.solution;

import edu.project2.maze.Cell;
import java.util.HashSet;
import java.util.Set;

public class DFSMazeSolver extends AbstractMazeSolver {
    private Set<Cell> used;

    @Override
    protected void findSolution() {
        used = new HashSet<>();
        dfs(start);
    }

    private boolean dfs(Cell cell) {
        if (cell.equals(end)) {
            path.add(cell);
            return true;
        }
        path.add(cell);
        used.add(cell);
        for (Cell neighbour : maze.paths().get(cell)) {
            if (!used.contains(neighbour)) {
                if (dfs(neighbour)) {
                    return true;
                }
            }
        }
        path.removeLast();
        return false;
    }
}

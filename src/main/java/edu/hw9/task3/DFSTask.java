package edu.hw9.task3;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class DFSTask extends RecursiveTask<Boolean> {
    private final Maze maze;
    private final List<Cell> path;
    private final Set<Cell> used;
    private final Cell end;
    private final Cell cell;

    public DFSTask(Maze maze, List<Cell> path, Set<Cell> used, Cell end, Cell cell) {
        this.maze = maze;
        this.path = path;
        this.used = used;
        this.end = end;
        this.cell = cell;
    }

    @Override
    protected Boolean compute() {
        used.add(cell);
        if (cell.equals(end)) {
            path.addFirst(cell);
            return true;
        }
        List<DFSTask> tasks = new ArrayList<>();
        for (Cell neighbour : maze.paths().get(cell)) {
            if (!used.contains(neighbour)) {
                DFSTask task = new DFSTask(maze, path, used, end, neighbour);
                tasks.add(task);
                task.fork();
            }
        }
        for (DFSTask task : tasks) {
            if (task.join()) {
                path.addFirst(cell);
                return true;
            }
        }
        return false;
    }
}

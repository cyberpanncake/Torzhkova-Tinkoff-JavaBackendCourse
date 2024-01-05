package edu.hw9.task3;

import edu.project2.maze.solution.AbstractMazeSolver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class ConcurrentDFSMazeSolver extends AbstractMazeSolver {


    @Override
    protected void findSolution() {
        path = Collections.synchronizedList(new ArrayList<>());
        DFSTask task = new DFSTask(maze, path, Collections.synchronizedSet(new HashSet<>()), end, start);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            forkJoinPool.invoke(task);
        }
    }
}

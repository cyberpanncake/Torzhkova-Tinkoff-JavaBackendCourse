package edu.project2.maze.generation;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractAddingPathsMazeGenerator implements IMazeGenerator {
    protected int n; // количество строк
    protected int m; // количество столбцов
    protected boolean[][] used;
    protected Map<Cell, Set<Cell>> paths;

    @Override
    public Maze generate(int n, int m) {
        if (n < 1 || m < 1) {
            throw new IllegalArgumentException("Размеры лабиринта должны быть больше 0");
        }
        this.n = n;
        this.m = m;
        used = new boolean[n][m];
        setInitialPaths();
        generatePaths();
        return new Maze(n, m, paths);
    }

    private void setInitialPaths() {
        paths = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                paths.put(new Cell(i, j), new HashSet<>());
            }
        }
    }

    protected abstract void generatePaths();
}

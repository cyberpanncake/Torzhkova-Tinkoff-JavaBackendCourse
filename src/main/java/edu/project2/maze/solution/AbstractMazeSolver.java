package edu.project2.maze.solution;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMazeSolver implements IMazeSolver {
    protected Maze maze;
    protected List<Cell> path;
    protected Cell start;
    protected Cell end;

    @Override
    public List<Cell> solve(Maze maze, Cell start, Cell end) {
        if (start.i() >= maze.n() || start.j() >= maze.m()) {
            throw new IllegalArgumentException("Начальная клетка имеет неверные координаты");
        }
        if (end.i() >= maze.n() || end.j() >= maze.m()) {
            throw new IllegalArgumentException("Конечная клетка имеет неверные координаты");
        }
        this.maze = maze;
        this.start = start;
        this.end = end;
        path = new ArrayList<>();
        findSolution();
        return path;
    }

    protected abstract void findSolution();
}

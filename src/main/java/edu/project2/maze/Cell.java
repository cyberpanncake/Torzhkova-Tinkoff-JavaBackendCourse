package edu.project2.maze;

import java.util.HashSet;
import java.util.Set;

public record Cell(int i, int j) {
    public Cell {
        if (i < 0 || j < 0) {
            throw new IllegalArgumentException("Координаты клетки лабиринта не должны быть отрицательными");
        }
    }

    public static Cell getRandom(int n, int m) {
        if (n < 1 || m < 1) {
            throw new IllegalArgumentException("Размеры лабиринта должны быть больше 0");
        }
        return new Cell((int) (Math.random() * n), (int) (Math.random() * m));
    }

    public static Set<Cell> getNeighbours(Cell cell, int n, int m) {
        Set<Cell> neighbours = new HashSet<>();
        int i = cell.i();
        int j = cell.j();
        if (i > 0) {
            neighbours.add(new Cell(i - 1, j));
        }
        if (i < n - 1) {
            neighbours.add(new Cell(i + 1, j));
        }
        if (j > 0) {
            neighbours.add(new Cell(i, j - 1));
        }
        if (j < m - 1) {
            neighbours.add(new Cell(i, j + 1));
        }
        return neighbours;
    }
}

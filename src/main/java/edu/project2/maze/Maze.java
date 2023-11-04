package edu.project2.maze;

import java.util.Map;
import java.util.Set;

public record Maze(int n, int m, Map<Cell, Set<Cell>> paths) {
    private static final int MAX_N = 20;
    private static final int MAX_M = 50;

    public Maze {
        if (n < 1 || m < 1) {
            throw new IllegalArgumentException("Размеры лабиринта должны быть больше 0");
        }
        if (n > MAX_N || m > MAX_M) {
            throw new IllegalArgumentException("Размеры лабиринта не должны превышать 20x50");
        }
        if (paths == null) {
            throw new IllegalArgumentException("Коллекция путей в лабиринте не должна быть равна null");
        }
    }
}

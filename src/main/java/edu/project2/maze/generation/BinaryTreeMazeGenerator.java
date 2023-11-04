package edu.project2.maze.generation;

import edu.project2.maze.Cell;

/**
 * Реализует алгоритм генерации на основе двоичных деревьев:
 * для каждой ячейки вырезается проход или вверх, или влево
 */
public class BinaryTreeMazeGenerator extends AbstractAddingPathsMazeGenerator {
    private static final double LEFT_PATH_PROBABILITY = 0.5;

    @Override
    protected void generatePaths() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int newI = i;
                int newJ = j;
                if (i == 0 || j == 0) {
                    newJ = j > 0 ? j - 1 : 0;
                    newI = i > 0 ? i - 1 : 0;
                } else {
                    if (Math.random() < LEFT_PATH_PROBABILITY) {
                        newJ = j - 1;
                    } else {
                        newI = i - 1;
                    }
                }
                Cell cell1 = new Cell(i, j);
                Cell cell2 = new Cell(newI, newJ);
                paths.get(cell1).add(cell2);
                paths.get(cell2).add(cell1);
            }
        }
    }
}

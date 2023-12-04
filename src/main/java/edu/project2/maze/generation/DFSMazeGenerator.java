package edu.project2.maze.generation;

import edu.project2.maze.Cell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Реализует алгоритм выращивания дерева с помощью обхода в глубину
 * со случайным выбором следующей ячейки из соседних
 */
public class DFSMazeGenerator extends AbstractAddingPathsMazeGenerator {

    @Override
    protected void generatePaths() {
        dfsGenerate(Cell.getRandom(n, m));
    }

    private void dfsGenerate(Cell cell) {
        used[cell.i()][cell.j()] = true;
        List<Cell> neighbours = new ArrayList<>(Cell.getNeighbours(cell, n, m));
        Collections.shuffle(neighbours);
        for (Cell neighbour : neighbours) {
            if (!used[neighbour.i()][neighbour.j()]) {
                paths.get(cell).add(neighbour);
                paths.get(neighbour).add(cell);
                dfsGenerate(neighbour);
            }
        }
    }
}

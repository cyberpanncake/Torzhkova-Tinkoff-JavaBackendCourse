package edu.project2.maze.solution;

import edu.project2.maze.Cell;

record SolutionTreeNode(SolutionTreeNode parent, Cell value) {
    SolutionTreeNode {
        if (value == null) {
            throw new IllegalArgumentException("Значение узла не может быть null");
        }
    }
}

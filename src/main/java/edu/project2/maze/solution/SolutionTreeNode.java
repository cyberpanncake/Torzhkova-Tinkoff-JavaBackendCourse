package edu.project2.maze.solution;

import edu.project2.maze.Cell;

public class SolutionTreeNode {
    private SolutionTreeNode parent;
    private Cell value;

    public SolutionTreeNode(SolutionTreeNode parent, Cell value) {
        if (value == null) {
            throw new IllegalArgumentException("Значение узла не может быть null");
        }
        this.parent = parent;
        this.value = value;
    }

    public SolutionTreeNode getParent() {
        return parent;
    }

    public Cell getValue() {
        return value;
    }
}

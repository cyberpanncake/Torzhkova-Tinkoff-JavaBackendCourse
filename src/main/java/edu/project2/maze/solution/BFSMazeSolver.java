package edu.project2.maze.solution;

import edu.project2.maze.Cell;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BFSMazeSolver extends AbstractMazeSolver {
    private Set<Cell> used;
    private SolutionTreeNode endNode;

    @Override
    protected void findSolution() {
        used = new HashSet<>();
        used.add(start);
        endNode = null;
        bfs(List.of(new SolutionTreeNode(null, start)));
        if (endNode != null) {
            buildSolutionPath(endNode);
        }
    }

    private void bfs(List<SolutionTreeNode> wave) {
        List<SolutionTreeNode> nextWave = new ArrayList<>();
        for (SolutionTreeNode node : wave) {
            Cell cell = node.value();
            if (cell.equals(end)) {
                endNode = node;
                return;
            }
            Set<Cell> neighbours = maze.paths().get(cell).stream()
                .filter(c -> !used.contains(c))
                .collect(Collectors.toSet());
            used.addAll(neighbours);
            nextWave.addAll(neighbours.stream()
                .map(c -> new SolutionTreeNode(node, c)).collect(Collectors.toSet()));
        }
        if (!nextWave.isEmpty()) {
            bfs(nextWave);
        }
    }

    private void buildSolutionPath(SolutionTreeNode node) {
        path.addFirst(node.value());
        if (node.parent() != null) {
            buildSolutionPath(node.parent());
        }
    }
}

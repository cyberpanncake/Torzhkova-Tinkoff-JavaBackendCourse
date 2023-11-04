package edu.project2;

import edu.project2.maze.Maze;
import edu.project2.maze.generation.BinaryTreeMazeGenerator;
import edu.project2.maze.generation.DFSMazeGenerator;
import edu.project2.view.MazeViewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        Maze maze = new BinaryTreeMazeGenerator().generate(8, 12);
        LOGGER.info(MazeViewer.getMazeView(maze));
    }
}

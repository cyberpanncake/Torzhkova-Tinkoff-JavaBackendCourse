package edu.project2.maze.generation;

import edu.project2.maze.Maze;

public interface IMazeGenerator {
    Maze generate(int n, int m);
}

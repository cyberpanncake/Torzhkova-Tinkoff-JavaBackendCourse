package edu.project2.view;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;

public class MazeViewer {

    private MazeViewer() {
    }

    public static String getMazeView(Maze maze) {
        PixelType[][] pixels = getPixels(maze);
        String resetColor = Color.RESET.getBackground();
        StringBuilder view = new StringBuilder(resetColor + "\n\n");
        for (int i = 0; i < maze.n() * 3 + 1; i += 2) {
            view.append(" ");
            for (int j = 0; j < maze.m() * 3 + 1; j++) {
                String foreground = pixels[i][j].getColor().getForeground();
                String background = i == maze.n() * 3 ? resetColor :
                    pixels[i + 1][j].getColor().getBackground();
                view.append(background + foreground + "▀");
            }
            view.append(resetColor + "\n");
        }
        return view.toString();
    }

    private static PixelType[][] getPixels(Maze maze) {
        PixelType[][] pixels = new PixelType[maze.n() * 3 + 1][maze.m() * 3 + 1];
        for (int i = 0; i < maze.n() * 3 + 1; i++) {
            for (int j = 0; j < maze.m() * 3 + 1; j++) {
                if (i % 3 == 0 || j % 3 == 0) {
                    pixels[i][j] = PixelType.WALL;
                } else {
                    pixels[i][j] = PixelType.FLOOR;
                }
            }
        }
        for (int i = 0; i < maze.n(); i++) {
            for (int j = 0; j < maze.m(); j++) {
                if (maze.paths().get(new Cell(i, j)).contains(new Cell(i + 1, j))) {
                    pixels[(i + 1) * 3][j * 3 + 1] = PixelType.FLOOR;
                    pixels[(i + 1) * 3][j * 3 + 2] = PixelType.FLOOR;
                }
                if (maze.paths().get(new Cell(i, j)).contains(new Cell(i, j + 1))) {
                    pixels[i * 3 + 1][(j + 1) * 3] = PixelType.FLOOR;
                    pixels[i * 3 + 2][(j + 1) * 3] = PixelType.FLOOR;
                }
            }
        }
        return pixels;
    }
}

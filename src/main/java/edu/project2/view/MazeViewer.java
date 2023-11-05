package edu.project2.view;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import java.util.List;

public class MazeViewer {

    private static final int CELL_PIXEL_SIZE = 3;

    private MazeViewer() {
    }

    public static String getMazeView(Maze maze) {
        checkMaze(maze);
        PixelType[][] pixels = getPixels(maze);
        return render(pixels);
    }

    public static String getMazeView(Maze maze, List<Cell> path) {
        checkMaze(maze);
        if (path == null) {
            throw new IllegalArgumentException("Путь не может быть null");
        }
        PixelType[][] pixels = getPixels(maze);
        if (!path.isEmpty()) {
            drawPath(pixels, path);
        }
        return render(pixels);
    }

    private static void checkMaze(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException("Лабиринт не может быть null");
        }
    }

    private static PixelType[][] getPixels(Maze maze) {
        PixelType[][] pixels = new PixelType[maze.n() * CELL_PIXEL_SIZE + 1][maze.m() * CELL_PIXEL_SIZE + 1];
        for (int i = 0; i < maze.n() * CELL_PIXEL_SIZE + 1; i++) {
            for (int j = 0; j < maze.m() * CELL_PIXEL_SIZE + 1; j++) {
                if (i % CELL_PIXEL_SIZE == 0 || j % CELL_PIXEL_SIZE == 0) {
                    pixels[i][j] = PixelType.WALL;
                } else {
                    pixels[i][j] = PixelType.FLOOR;
                }
            }
        }
        for (int i = 0; i < maze.n(); i++) {
            for (int j = 0; j < maze.m(); j++) {
                if (maze.paths().get(new Cell(i, j)).contains(new Cell(i + 1, j))) {
                    pixels[(i + 1) * CELL_PIXEL_SIZE][j * CELL_PIXEL_SIZE + 1] = PixelType.FLOOR;
                    pixels[(i + 1) * CELL_PIXEL_SIZE][j * CELL_PIXEL_SIZE + 2] = PixelType.FLOOR;
                }
                if (maze.paths().get(new Cell(i, j)).contains(new Cell(i, j + 1))) {
                    pixels[i * CELL_PIXEL_SIZE + 1][(j + 1) * CELL_PIXEL_SIZE] = PixelType.FLOOR;
                    pixels[i * CELL_PIXEL_SIZE + 2][(j + 1) * CELL_PIXEL_SIZE] = PixelType.FLOOR;
                }
            }
        }
        return pixels;
    }

    private static void drawPath(PixelType[][] pixels, List<Cell> path) {
        if (checkIfStartIsEnd(pixels, path)) {
            return;
        }
        for (int k = 0; k < path.size(); k++) {
            Cell cell = path.get(k);
            PixelType type = k == 0 ? PixelType.START_CELL : k == path.size() - 1 ? PixelType.END_CELL : PixelType.PATH;
            int i = cell.i() * CELL_PIXEL_SIZE + 1;
            int j = cell.j() * CELL_PIXEL_SIZE + 1;
            drawCell(pixels, i, j, type);
            if (k < path.size() - 1) {
                drawPathBetweenCells(pixels, path, k, i, j);
            }
        }
    }

    private static boolean checkIfStartIsEnd(PixelType[][] pixels, List<Cell> path) {
        if (path.getFirst().equals(path.getLast())) {
            int i = path.getFirst().i() * CELL_PIXEL_SIZE + 1;
            int j = path.getFirst().j() * CELL_PIXEL_SIZE + 1;
            pixels[i][j] = PixelType.START_CELL;
            pixels[i + 1][j] = PixelType.START_CELL;
            pixels[i][j + 1] = PixelType.END_CELL;
            pixels[i + 1][j + 1] = PixelType.END_CELL;
            return true;
        }
        return false;
    }

    private static void drawCell(PixelType[][] pixels, int i, int j, PixelType type) {
        pixels[i][j] = type;
        pixels[i][j + 1] = type;
        pixels[i + 1][j] = type;
        pixels[i + 1][j + 1] = type;
    }

    private static void drawPathBetweenCells(PixelType[][] pixels, List<Cell> path, int k, int i, int j) {
        Cell nextCell = path.get(k + 1);
        int nextI = nextCell.i() * CELL_PIXEL_SIZE + 1;
        int nextJ = nextCell.j() * CELL_PIXEL_SIZE + 1;
        int startI = i == nextI ? i : (Math.max(i, nextI) - 1);
        int n = Math.abs(i - nextI) == CELL_PIXEL_SIZE ? 1 : 2;
        int startJ = j == nextJ ? j : (Math.max(j, nextJ) - 1);
        int m = Math.abs(j - nextJ) == CELL_PIXEL_SIZE ? 1 : 2;
        for (int p = 0; p < n; p++) {
            for (int s = 0; s < m; s++) {
                pixels[p + startI][s + startJ] = PixelType.PATH;
            }
        }
    }

    public static String render(PixelType[][] pixels) {
        int n = pixels.length;
        int m = pixels[0].length;
        String resetColor = Color.RESET.getBackground();
        StringBuilder view = new StringBuilder(resetColor + "\n\n");
        for (int i = 0; i < n; i += 2) {
            view.append(" ");
            for (int j = 0; j < m; j++) {
                String foreground = pixels[i][j].getColor().getForeground();
                String background = i == n - 1 ? resetColor
                    : pixels[i + 1][j].getColor().getBackground();
                view.append(background).append(foreground).append("▀");
            }
            view.append(resetColor).append("\n");
        }
        return view.toString();
    }
}

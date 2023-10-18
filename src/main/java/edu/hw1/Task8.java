package edu.hw1;

public class Task8 {
    private static final int BOARD_SIZE = 8;
    private static final String BOARD_SIZE_ERROR_MESSAGE = "Размеры доски не соответствуют 8x8";

    public boolean knightBoardCapture(int[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Входная матрица не может быть null");
        }
        if (board.length != BOARD_SIZE) {
            throw new IllegalArgumentException(BOARD_SIZE_ERROR_MESSAGE);
        }
        for (int[] line : board) {
            if (line.length != BOARD_SIZE) {
                throw new IllegalArgumentException(BOARD_SIZE_ERROR_MESSAGE);
            }
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != 0 && board[i][j] != 1) {
                    throw new IllegalArgumentException("Клетка доски может быть равной только 0 или 1");
                }
                if (board[i][j] == 1) {
                    if (findKnightNearby(board, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean findKnightNearby(int[][] board, int i, int j) {
        for (int y = Math.max(0, i - 2); y <= Math.min(BOARD_SIZE - 1, i + 2); y++) {
            for (int x = Math.max(0, j - 2); x <= Math.min(BOARD_SIZE - 1, j + 2); x++) {
                int yDist = Math.abs(i - y);
                int xDist = Math.abs(j - x);
                if (!(yDist < 2 && xDist < 2) && !(yDist == 2 && xDist == 2) && !(i == y || j == x)) {
                    if (board[y][x] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

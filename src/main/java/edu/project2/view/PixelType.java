package edu.project2.view;

public enum PixelType {
    WALL(Color.GRAY),
    FLOOR(Color.BLACK),
    PATH(Color.GREEN),
    START_CELL(Color.YELLOW),
    END_CELL(Color.RED);

    private Color color;

    PixelType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

package edu.project2.view;

public enum PixelType {
    WALL(Color.BLACK),
    FLOOR(Color.GRAY),
    PATH(Color.BLUE),
    START_CELL(Color.GREEN),
    END_CELL(Color.RED);

    private Color color;

    PixelType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

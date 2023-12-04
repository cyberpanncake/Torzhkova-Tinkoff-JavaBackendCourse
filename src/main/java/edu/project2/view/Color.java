package edu.project2.view;

public enum Color {
    RESET("\u001B[0m"),
    BLACK("\u001B[40m", "\u001B[30m"),
    BLUE("\u001B[44m", "\u001B[34m"),
    GREEN("\u001B[42m", "\u001B[32m"),
    RED("\u001B[41m", "\u001B[31m"),
    YELLOW("\u001B[43m", "\u001B[33m"),
    GRAY("\u001B[47m", "\u001B[37m");

    private final String background;
    private final String foreground;

    Color(String background, String foreground) {
        this.background = background;
        this.foreground = foreground;
    }

    Color(String color) {
        this.background = color;
        this.foreground = color;
    }

    public String getBackground() {
        return background;
    }

    public String getForeground() {
        return foreground;
    }
}

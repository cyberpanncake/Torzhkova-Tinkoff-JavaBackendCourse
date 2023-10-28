package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }

    @Override
    public Square setWidth(int width) {
        return setSide(width);
    }

    @Override
    public Square setHeight(int height) {
        return setSide(height);
    }

    public Square setSide(int side) {
        return new Square(side);
    }
}

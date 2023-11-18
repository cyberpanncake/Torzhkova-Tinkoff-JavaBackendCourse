package edu.hw3.task6;

public record Stock(int id, double price) implements Comparable<Stock> {

    public Stock {
        if (price < 0) {
            throw new IllegalArgumentException("Цена акции должна быть положительной");
        }
    }

    @Override
    public int compareTo(Stock o) {
        return (int) (this.price - o.price);
    }
}

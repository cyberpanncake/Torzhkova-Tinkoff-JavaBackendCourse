package edu.hw3.task6;

import java.util.PriorityQueue;

public class MyStockMarket implements StockMarket {
    private static final String NULL_STOCK_MESSAGE = "Акция не должна быть null";

    private final PriorityQueue<Stock> queue = new PriorityQueue<>((o1, o2) -> -o1.compareTo(o2));

    @Override
    public void add(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException(NULL_STOCK_MESSAGE);
        }
        queue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException(NULL_STOCK_MESSAGE);
        }
        queue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return queue.peek();
    }
}

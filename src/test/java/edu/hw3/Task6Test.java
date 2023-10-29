package edu.hw3;

import edu.hw3.task6.MyStockMarket;
import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.UUID;

public class Task6Test {

    @ParameterizedTest
    @MethodSource("parameters")
    public void buildStockExceptionTest(int id, double price) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Stock(id, price));
    }

    private static Arguments[] parameters() {
        return new Arguments[] {
            Arguments.of(1, -2.5),
            Arguments.of(2, -5.5)
        };
    }

    @Test
    public void mostValuableStockTest() {
        StockMarket market = new MyStockMarket();

        Assertions.assertNull(market.mostValuableStock());
    }

    @Test
    public void removeTest() {
        StockMarket market = new MyStockMarket();
        Stock stock = new Stock(1, 1.0);

        market.remove(stock);
        Assertions.assertNull(market.mostValuableStock());

    }

    @Test
    public void addTest() {
        StockMarket market = new MyStockMarket();
        Stock stock = new Stock(1, 1.0);

        market.add(stock);
        Assertions.assertEquals(stock, market.mostValuableStock());

        market.remove(stock);
        Assertions.assertNull(market.mostValuableStock());
    }

    @Test
    public void mostValuableStockSortedTest() {
        StockMarket market = new MyStockMarket();

        for (int i = 10; i > 0; i--) {
            market.add(new Stock(i, i));
        }

        for (int i = 10; i > 0; i--) {
            var mostValuableStock = market.mostValuableStock();
            Assertions.assertEquals(i, market.mostValuableStock().price());
            market.remove(mostValuableStock);
        }
        Assertions.assertNull(market.mostValuableStock());
    }
}

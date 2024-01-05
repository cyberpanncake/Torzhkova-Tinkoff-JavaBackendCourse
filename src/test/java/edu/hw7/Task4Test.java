package edu.hw7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task4Test {
    private final static Logger LOGGER = LogManager.getLogger();

    @ParameterizedTest
    @MethodSource("params")
    void piTest(long n, double delta) {
        double expected = Math.PI;
        double actual = Task4.pi(n);
        Assertions.assertEquals(expected, actual, delta);
    }

    @ParameterizedTest
    @MethodSource("params")
    void piConcurrentDefaultTest(long n, double delta) throws InterruptedException {
        double expected = Math.PI;
        double actual = Task4.piConcurrent(n);
        Assertions.assertEquals(expected, actual, delta);
    }

    @ParameterizedTest
    @MethodSource("params")
    void piConcurrentPerformanceTest(long n, double delta) throws InterruptedException {
        StringBuilder performance = new StringBuilder();
        performance.append("\nn = %,d\n".formatted(n));
        double expected = Math.PI;

        long time = System.nanoTime();
        double actual = Task4.pi(n);
        time = System.nanoTime() - time;
        double time1 = time;
        Assertions.assertEquals(expected, actual, delta);
        performance.append("Однопоточная:\n")
            .append(getResult(time, expected, actual));

        long timeSum = 0;
        int[] threadsCount = new int[] { 2, 4, 8 };
        for (int t : threadsCount) {
            time = System.nanoTime();
            actual = Task4.piConcurrent(n, t);
            time = System.nanoTime() - time;
            timeSum += time;
            Assertions.assertEquals(expected, actual, delta);
            performance.append("\nМногопоточная (кол-во потоков %d):\n".formatted(t))
                    .append(getResult(time, expected, actual));
        }
        performance.append("\nСреднее ускорение решения: %f\n"
            .formatted(time1 / (timeSum / (double) threadsCount.length)));
        LOGGER.info(performance.toString());
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of(10_000_000, 0.005),
            Arguments.of(100_000_000, 0.001),
            Arguments.of(1_000_000_000, 0.0005)
        );
    }

    // абсолютная погрешность
    private double absE(double expected, double actual) {
        return Math.abs(expected - actual);
    }

    // относительная погрешность
    private double relE(double expected, double actual) {
        return absE(expected, actual) / expected;
    }

    private String getResult(long time, double expected, double actual) {
        return """
            Время работы:               %d с %d мс %d мкс %d нс
            Абсолютная погрешность:     %.15f
            Относительная погрешность:  %.15f
            """.formatted(time / 1_000_000_000, time / 1_000_000 % 1_000, time / 1_000 % 1_000, time % 1_000,
            absE(expected, actual), relE(expected, actual));
    }

    @Test
    void piNExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task4.pi(0));
    }

    @Test
    void piThreadCountExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task4.piConcurrent(10, 0));
    }
}

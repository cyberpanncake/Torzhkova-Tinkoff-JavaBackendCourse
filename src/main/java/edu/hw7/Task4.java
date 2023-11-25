package edu.hw7;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Task4 {
    private static final int TREADS_COUNT = 2;

    private Task4() {}

    @SuppressWarnings("MagicNumber")
    public static double pi(long n) {
        checkN(n);
        long circleCount = 0;
        long totalCount = 0;
        for (long i = 0; i < n; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            if (x * x + y * y <= 1) {
                circleCount++;
            }
            totalCount++;
        }
        return 4 * circleCount / (double) totalCount;
    }

    public static double piConcurrent(long n) throws InterruptedException {
        return piConcurrent(n, TREADS_COUNT);
    }

    public static double piConcurrent(long n, int threadsCount) throws InterruptedException {
        checkN(n);
        if (threadsCount < 2) {
            throw new IllegalArgumentException("Потоков должно быть хотя бы 2");
        }
        Thread[] threads = new Thread[threadsCount];
        double[] answer = new double[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            int threadI = i;
            long threadN = n / threadsCount + (i == threadsCount - 1 ? n % threadsCount : 0);
            threads[i] = new Thread(() -> answer[threadI] = pi(threadN));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return Arrays.stream(answer).sum() / (double) threadsCount;
    }

    private static void checkN(long n) {
        if (n < 1) {
            throw new IllegalArgumentException("n должно быть положительным");
        }
    }
}
